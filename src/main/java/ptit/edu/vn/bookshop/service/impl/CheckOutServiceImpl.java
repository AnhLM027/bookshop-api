package ptit.edu.vn.bookshop.service.impl;

import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.dto.response.CartResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CheckOutResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Address;
import ptit.edu.vn.bookshop.domain.entity.Cart;
import ptit.edu.vn.bookshop.domain.entity.User;
import ptit.edu.vn.bookshop.exception.UsernameNotFoundException;
import ptit.edu.vn.bookshop.repository.AddressRepository;
import ptit.edu.vn.bookshop.repository.CouponRepository;
import ptit.edu.vn.bookshop.service.*;
import ptit.edu.vn.bookshop.util.security.SecurityUtil;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CheckOutServiceImpl implements CheckOutService {
    private final UserService userService;
    private final CartService cartService;
    private final AddressService addressService;
    private final CouponService couponService;

    public CheckOutServiceImpl(UserService userService, CartService cartService, AddressService addressService,
                                CouponService couponService) {
        this.userService = userService;
        this.cartService = cartService;
        this.addressService = addressService;
        this.couponService = couponService;
    }

    @Override
    public CheckOutResponseDTO getCheckOutResponseDTO() {
//        String email = SecurityUtil.getCurrentUserLogin()
//                .orElseThrow(() -> new UsernameNotFoundException("User is not found."));
//        User user = this.userService.getUserByUsername(email);
//        // lấy thông tin về giỏ hàng
////        Cart cart = this.cartService.getCartItems();
////
////        // lấy lên thông tin về địa chỉ
////        Address address = this.addressService.getAddress();
//
////        Address address = this.addressRepository.findByUserAndIsDefaultTrue(user)
////                .orElseThrow(() -> new IllegalStateException("No default address found"));
//
//        CheckOutResponseDTO.ShippingAddress shippingAddress = new CheckOutResponseDTO.ShippingAddress();
//        shippingAddress.setName(address.getReceiverName());
//        shippingAddress.setPhone(address.getPhone());
//        String fullAddress  = Stream.of(
//                        address.getStreet(),
//                        address.getWard(),
//                        address.getDistrict(),
//                        address.getCity()
//                )
//                .filter(Objects::nonNull)
//                .collect(Collectors.joining(", "));
//        shippingAddress.setAddress(fullAddress);
//        // coupon
////        Coupon coupon = this.couponRepository.findByCode()
//
//        CheckOutResponseDTO.SummaryCheckout summaryCheckout = new CheckOutResponseDTO.SummaryCheckout();
//        summaryCheckout.setSubtotal(cartResponseDTO.getSummary().getSubtotal());
//        summaryCheckout.setTotalQuantity(cartResponseDTO.getSummary().getTotalQuantity());
//
//        CheckOutResponseDTO checkOutResponseDTO = new CheckOutResponseDTO();
//        checkOutResponseDTO.setShippingAddress(shippingAddress);
//        checkOutResponseDTO.setItems(cartResponseDTO.getCartItems());
//        checkOutResponseDTO.setPaymentMethods("COD");
//        checkOutResponseDTO.setSummary(summaryCheckout);
//        return checkOutResponseDTO;
        return null;
    }
}
