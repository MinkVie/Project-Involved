package r2s.com.demo.SpringWebDemo.service;

import r2s.com.demo.SpringWebDemo.dto.request.InsertCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CartResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCartDatabase();

    PageResponseDTO getCartPaging();

    Cart insertCart(InsertCartRequestDTO requestDTO);

    CartResponseDTO getCartbyId(Integer id);

    CartResponseDTO updateCart(UpdateCartRequestDTO requestDTO, Integer id);

    void deleteCartbyId(Integer id);

    CartResponseDTO getAddressByCartId(int cartId);
}
