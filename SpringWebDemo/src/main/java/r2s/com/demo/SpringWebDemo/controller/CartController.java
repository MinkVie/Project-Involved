package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.InsertCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CartResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CartResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Cart;
import r2s.com.demo.SpringWebDemo.service.CartService;

import java.util.List;

@RestController()
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    @GetMapping
    public ResponseEntity<?> getCartPaging() {
        PageResponseDTO pageResponseDTO = cartService.getCartPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{cart-id}")
    public ResponseEntity<?> getCartById(@PathVariable(value = "cart-id") int cartId) {
        CartResponseDTO CartResponseDTO = cartService.getCartbyId(cartId);
        return new ResponseEntity<>(CartResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{cart-id}/address")
    public ResponseEntity<?> getAddressByCartId(@PathVariable(value = "cart-id") int cartId) {
        CartResponseDTO CartResponseDTO = cartService.getAddressByCartId(cartId);
        return new ResponseEntity<>(CartResponseDTO, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity insertCart(@RequestBody InsertCartRequestDTO requestDTO) {
        Cart cart = cartService.insertCart(requestDTO);
        return new ResponseEntity(cart, HttpStatus.OK);
    }

    @PutMapping("/{Cart-id}")
    public ResponseEntity updateCart(@PathVariable(value = "Cart-id") int CartId,
                                     @RequestBody UpdateCartRequestDTO updateCartRequestDTO) {
        CartResponseDTO response = cartService.updateCart(updateCartRequestDTO, CartId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/{Cart-id}")
    public ResponseEntity deleteCart(@PathVariable(value = "Cart-id") int CartId) {
        cartService.deleteCartbyId(CartId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    
}
