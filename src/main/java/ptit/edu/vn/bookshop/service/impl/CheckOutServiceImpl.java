package ptit.edu.vn.bookshop.service.impl;

import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.dto.response.CartResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CheckOutResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Address;
import ptit.edu.vn.bookshop.domain.entity.User;
import ptit.edu.vn.bookshop.exception.UsernameNotFoundException;
import ptit.edu.vn.bookshop.repository.AddressRepository;
import ptit.edu.vn.bookshop.service.CartService;
import ptit.edu.vn.bookshop.service.CheckOutService;
import ptit.edu.vn.bookshop.service.UserService;
import ptit.edu.vn.bookshop.util.security.SecurityUtil;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CheckOutServiceImpl implements CheckOutService {
    private final UserService userService;
    private final CartService cartService;
    private final AddressRepository addressRepository;

    public CheckOutServiceImpl(UserService userService, CartService cartService,  AddressRepository addressRepository) {
        this.userService = userService;
        this.cartService = cartService;
        this.addressRepository = addressRepository;
    }

    @Override
    public CheckOutResponseDTO getCheckOutResponseDTO() {
        String email = SecurityUtil.getCurrentUserLogin()
                .orElseThrow(() -> new UsernameNotFoundException("User is not found."));
        User user = this.userService.getUserByUsername(email);

        CartResponseDTO cartResponseDTO = this.cartService.getCartItems();

        Address address = this.addressRepository.findByUserAndIsDefaultTrue(user)
                .orElseThrow(() -> new IllegalStateException("No default address found"));

        CheckOutResponseDTO.ShippingAddress shippingAddress = new CheckOutResponseDTO.ShippingAddress();
        shippingAddress.setName(address.getReceiverName());
        shippingAddress.setPhone(address.getPhone());
        String fullAddress  = Stream.of(
                        address.getStreet(),
                        address.getWard(),
                        address.getDistrict(),
                        address.getCity()
                )
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));
        shippingAddress.setAddress(fullAddress);

        CheckOutResponseDTO.SummaryCheckout summaryCheckout = new CheckOutResponseDTO.SummaryCheckout();
        summaryCheckout.setSubtotal(cartResponseDTO.getSummary().getSubtotal());
        summaryCheckout.setTotalQuantity(cartResponseDTO.getSummary().getTotalQuantity());

        CheckOutResponseDTO checkOutResponseDTO = new CheckOutResponseDTO();
        checkOutResponseDTO.setShippingAddress(shippingAddress);
        checkOutResponseDTO.setItems(cartResponseDTO.getCartItems());
        checkOutResponseDTO.setPaymentMethods("COD");
        checkOutResponseDTO.setSummary(summaryCheckout);
        return checkOutResponseDTO;
    }
}
