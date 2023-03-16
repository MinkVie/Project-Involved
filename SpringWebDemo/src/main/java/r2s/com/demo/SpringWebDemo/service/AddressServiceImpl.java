package r2s.com.demo.SpringWebDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import r2s.com.demo.SpringWebDemo.dto.request.InsertAddressRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateAddressRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.AddressResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Address;
import r2s.com.demo.SpringWebDemo.entity.Cart;
import r2s.com.demo.SpringWebDemo.entity.User;
import r2s.com.demo.SpringWebDemo.mapper.AddressMapper;
import r2s.com.demo.SpringWebDemo.mapper.CartMapper;
import r2s.com.demo.SpringWebDemo.mapper.UserMapper;
import r2s.com.demo.SpringWebDemo.repository.AddressRepository;

import java.util.List;
@Component
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Address> getAllAddressDatabase() {
        Iterable<Address> addressIterable = addressRepository.findAll();
        return (List<Address>) addressIterable;
    }
    @Override
    public PageResponseDTO getAddressPaging() {
        Pageable pageable = PageRequest.of(0,2);
        Page<Address> addressPage = addressRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get address by paging"));

        PageResponseDTO pageResponseDTO = new PageResponseDTO();

        pageResponseDTO.setPage(addressPage.getNumber());
        pageResponseDTO.setSize(addressPage.getSize());
        pageResponseDTO.setTotalPages(addressPage.getTotalPages());
        pageResponseDTO.setTotalRecord(addressPage.getTotalElements());

        List<AddressResponseDTO> addressResponseDTOS = addressMapper.convertEntityToResponseDtos(addressPage.getContent());
        pageResponseDTO.setData(addressResponseDTOS);

        return pageResponseDTO;
    }

    @Override
    @Transactional
    public AddressResponseDTO insertAddress(InsertAddressRequestDTO requestDTO) {
        Address address= new Address();

        address.setName(requestDTO.getName());

        address.setDistrict(requestDTO.getDistrict());
        address.setPhone(requestDTO.getPhone());
        address.setProvince(requestDTO.getProvince());
        address.setStreet(requestDTO.getStreet());

        addressRepository.save(address);

        AddressResponseDTO addressResponseDTO = addressMapper.convertEntityToResponseDto(address);
        return addressResponseDTO;
    }

    @Override
    public AddressResponseDTO getAddressbyId(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't get address by this id"));

        List<User> users = address.getUsers();
        Cart cart = address.getCart();

        AddressResponseDTO addressResponseDTOS = addressMapper.convertEntityToResponseDto(address);
        addressResponseDTOS.setUser(userMapper.convertEntityToResponseDtos(users));
        addressResponseDTOS.setCart(cartMapper.convertEntityToResponseDto(cart));

        return addressResponseDTOS;
    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(UpdateAddressRequestDTO requestDTO, Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't update address by this id"));

        address.setName(requestDTO.getName());
        address.setDefaultAddress(requestDTO.isDefaultAddress());
        address.setDeleted(requestDTO.isDeleted());
        address.setDistrict(requestDTO.getDistrict());
        address.setPhone(requestDTO.getPhone());
        address.setProvince(requestDTO.getProvince());
        address.setStreet(requestDTO.getStreet());
        address.setType(requestDTO.isType());

        addressRepository.save(address);

        AddressResponseDTO addressResponseDTO = addressMapper.convertEntityToResponseDto(address);
        return addressResponseDTO;
    }

    @Override
    @Transactional
    public void deleteAddressbyId(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't delete address by this id"));

        addressRepository.delete(address);
    }
}
