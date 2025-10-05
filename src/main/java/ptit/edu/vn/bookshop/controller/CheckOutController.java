package ptit.edu.vn.bookshop.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.edu.vn.bookshop.domain.dto.request.ShippingAddressRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.CheckOutResponseDTO;
import ptit.edu.vn.bookshop.service.CheckOutService;
import ptit.edu.vn.bookshop.util.anotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class CheckOutController {

    private final CheckOutService checkOutService;

    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PatchMapping("/checkout/shipping-address")
    @ApiMessage("Shipping address updated successfully")
    public ResponseEntity<CheckOutResponseDTO> updateShippingAddress(@Valid @RequestBody ShippingAddressRequestDTO shippingAddressRequestDTO) {
        CheckOutResponseDTO checkOutResponseDTO = this.checkOutService.updateShippingAddress(shippingAddressRequestDTO);
        return ResponseEntity.ok().body(checkOutResponseDTO);
    }

    @GetMapping("/checkout")
    @ApiMessage("Checkout information retrieved successfully")
    public ResponseEntity<CheckOutResponseDTO> checkout(){
        CheckOutResponseDTO checkOutResponseDTO = this.checkOutService.getCheckOutResponseDTO();
        return ResponseEntity.ok().body(checkOutResponseDTO);
    }

    // con thieu endpint payment_method
    // discount
}