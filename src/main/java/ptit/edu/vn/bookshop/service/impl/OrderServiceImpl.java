package ptit.edu.vn.bookshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.edu.vn.bookshop.domain.constant.BookStatusEnum;
import ptit.edu.vn.bookshop.domain.constant.OrderStatusEnum;
import ptit.edu.vn.bookshop.domain.dto.request.OrderRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.OrderResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.*;
import ptit.edu.vn.bookshop.exception.UsernameNotFoundException;
import ptit.edu.vn.bookshop.repository.BookRepository;
import ptit.edu.vn.bookshop.repository.CartItemRepository;
import ptit.edu.vn.bookshop.repository.CartRepository;
import ptit.edu.vn.bookshop.repository.OrderRepository;
import ptit.edu.vn.bookshop.service.OrderService;
import ptit.edu.vn.bookshop.service.UserService;
import ptit.edu.vn.bookshop.service.mapper.OrderMapper;
import ptit.edu.vn.bookshop.util.security.SecurityUtil;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;
    private final BookRepository bookRepository;

    public OrderServiceImpl(UserService userService, CartRepository cartRepository, OrderRepository orderRepository,
                            CartItemRepository cartItemRepository, OrderMapper orderMapper, BookRepository bookRepository) {
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderMapper = orderMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        // lay thong tin cua nguoi dung len
        String email = SecurityUtil.getCurrentUserLogin()
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
        User user = this.userService.getUserByUsername(email);
        // lay len thong tin cua gio hang
        Cart cart = this.cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for user"));

        List<Long> cartItemIds = orderRequestDTO.getCartItems().stream()
                .map(OrderRequestDTO.ItemRequestDTO::getId).toList();

        List<CartItem> items = cart.getCartItems().stream()
                .filter(it -> cartItemIds.contains(it.getId())).toList();

        if (items.size() != cartItemIds.size()) {
            throw new IllegalArgumentException("Some cart items are invalid or do not belong to the current cart");
        }

        Order order = new Order();
        order.setUser(user);
        order.setReceiverName(orderRequestDTO.getShippingAddress().getName());
        order.setReceiverPhone(orderRequestDTO.getShippingAddress().getPhone());
        order.setReceiverAddress(orderRequestDTO.getShippingAddress().getAddress());
        order.setOrderDate(Instant.now());
        order.setPaymentMethod("CASH");
        order.setNotes(orderRequestDTO.getNote());
        order.setStatus(OrderStatusEnum.PENDING);

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(cartItem.getUnitPrice().multiply(BigDecimal.ONE.subtract(cartItem.getItemDiscount())));
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setBook(cartItem.getBook());
            orderItems.add(orderItem);

            // update book
            Book book = cartItem.getBook();
            int newQuantity = book.getQuantity() - cartItem.getQuantity();
            book.setQuantity(Math.max(newQuantity, 0));
            if(book.getQuantity() <= 0) {
                book.setStatus(BookStatusEnum.OUT_OF_STOCK);
            }
        }
        order.setOrderItems(orderItems);

        BigDecimal totalPrice = orderItems.stream()
                .map(it -> it.getPrice().multiply(BigDecimal.valueOf(it.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //hard code
        BigDecimal shippingFee = BigDecimal.valueOf(10_000);
        BigDecimal discountFee = totalPrice.multiply(BigDecimal.valueOf(0.1));
        BigDecimal finalPrice = totalPrice.add(shippingFee).subtract(discountFee);

        order.setTotalPrice(totalPrice);
        order.setShippingFee(shippingFee);
        order.setDiscountFee(discountFee);
        order.setFinalPrice(finalPrice);

        this.orderRepository.save(order);

        // xóa các sản phẩm mà khách hàng mua
        for (CartItem cartItem : items) {
            cart.getCartItems().remove(cartItem);
            this.cartItemRepository.delete(cartItem);
        }

        // Nếu giỏ hàng trống thì xóa cart
        if (cart.getCartItems().isEmpty()) {
            this.cartRepository.delete(cart);
        } else {
            // Nếu còn items, cập nhật lại cart
            this.cartRepository.save(cart);
        }

        return this.orderMapper.toOrderResponseDTO(order);
    }
}
