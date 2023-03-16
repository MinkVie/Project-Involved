package r2s.com.demo.SpringWebDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import r2s.com.demo.SpringWebDemo.dto.request.InsertCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.AddressResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CartResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Address;
import r2s.com.demo.SpringWebDemo.entity.Cart;
import r2s.com.demo.SpringWebDemo.mapper.AddressMapper;
import r2s.com.demo.SpringWebDemo.mapper.CartMapper;
import r2s.com.demo.SpringWebDemo.repository.CartRepository;

import java.util.List;
@Component
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Cart> getAllCartDatabase() {
        Iterable<Cart> cartIterable = cartRepository.findAll();
        return (List<Cart>) cartIterable;
    }
    @Override
    public PageResponseDTO getCartPaging() {
        Pageable pageable = PageRequest.of(0,2);
        Page<Cart> cartPage = cartRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get cart by paging"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(cartPage.getNumber());
        pageResponseDTO.setSize(cartPage.getSize());
        pageResponseDTO.setTotalPages(cartPage.getTotalPages());
        pageResponseDTO.setTotalRecord(cartPage.getTotalElements());

        List<CartResponseDTO> cartResponseDTOS = cartMapper.convertEntityToResponseDtos(cartPage.getContent());
        pageResponseDTO.setData(cartResponseDTOS);

        return pageResponseDTO;
    }
    @Override
    @Transactional
    public Cart insertCart(InsertCartRequestDTO requestDTO) {
        Cart cart= new Cart();

        cart.setUserId(requestDTO.getUserId());

        cartRepository.save(cart);

        return cart;
    }

    @Override
    public CartResponseDTO getCartbyId(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't get cart by this id"));

        CartResponseDTO cartResponseDTOS = cartMapper.convertEntityToResponseDto(cart);

        return cartResponseDTOS;
    }

    @Override
    @Transactional
    public CartResponseDTO updateCart(UpdateCartRequestDTO requestDTO, Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't update cart by this id"));

        cart.setUserId(requestDTO.getUserId());

        cartRepository.save(cart);

        CartResponseDTO cartResponseDTO = cartMapper.convertEntityToResponseDto(cart);

        return cartResponseDTO;
    }

    @Override
    @Transactional
    public void deleteCartbyId(Integer id) {

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't delete cart by this id"));

        cartRepository.delete(cart);
    }

    @Override
    public CartResponseDTO getAddressByCartId(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Can't delete cart by this id"));

        Address address = cart.getAddress();

        AddressResponseDTO addressResponseDTO = addressMapper.convertEntityToResponseDto(address);
        CartResponseDTO cartResponseDTO= cartMapper.convertEntityToResponseDto(cart);
        cartResponseDTO.setAddressResponseDTO(addressResponseDTO);

        return cartResponseDTO;
    }
}
