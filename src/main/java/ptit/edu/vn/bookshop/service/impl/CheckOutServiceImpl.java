package ptit.edu.vn.bookshop.service.impl;

import org.springframework.stereotype.Service;
import ptit.edu.vn.bookshop.domain.dto.request.ShippingAddressRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CartResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CheckOutResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.User;
import ptit.edu.vn.bookshop.exception.UsernameNotFoundException;
import ptit.edu.vn.bookshop.service.CartService;
import ptit.edu.vn.bookshop.service.CheckOutService;
import ptit.edu.vn.bookshop.service.UserService;
import ptit.edu.vn.bookshop.service.mapper.CheckOutMapper;
import ptit.edu.vn.bookshop.util.security.SecurityUtil;

@Service
public class CheckOutServiceImpl implements CheckOutService {
    private final UserService userService;
    private final CartService cartService;
    private final CheckOutMapper checkOutMapper;

    public CheckOutServiceImpl(UserService userService, CartService cartService, CheckOutMapper checkOutMapper) {
        this.userService = userService;
        this.cartService = cartService;
        this.checkOutMapper = checkOutMapper;
    }

    @Override
    public CheckOutResponseDTO getCheckOutResponseDTO() {
        String email = SecurityUtil.getCurrentUserLogin()
                .orElseThrow(() -> new UsernameNotFoundException("Username is null"));
        User user = this.userService.getUserByUsername(email);

        CartResponseDTO cartResponseDTO = this.cartService.getCartItems();
        return this.checkOutMapper.toCheckOutResponseDTO(user, cartResponseDTO);
    }

    @Override
    public CheckOutResponseDTO updateShippingAddress(ShippingAddressRequestDTO shippingAddress) {
        String email = SecurityUtil.getCurrentUserLogin()
                .orElseThrow(() -> new UsernameNotFoundException("Username is null"));

        User user = this.userService.getUserByUsername(email);

        if (shippingAddress.getName() != null) user.setName(shippingAddress.getName());
        if (shippingAddress.getPhone() != null) user.setPhone(shippingAddress.getPhone());
        if (shippingAddress.getAddress() != null) user.setAddress(shippingAddress.getAddress());

        CartResponseDTO cartResponseDTO = this.cartService.getCartItems();

        return this.checkOutMapper.toCheckOutResponseDTO(user, cartResponseDTO);
    }
}
