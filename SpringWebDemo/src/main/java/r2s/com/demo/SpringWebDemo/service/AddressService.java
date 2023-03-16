package r2s.com.demo.SpringWebDemo.service;

import r2s.com.demo.SpringWebDemo.dto.request.InsertAddressRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateAddressRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.AddressResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddressDatabase();

    PageResponseDTO getAddressPaging();

    AddressResponseDTO insertAddress(InsertAddressRequestDTO requestDTO);

    AddressResponseDTO getAddressbyId(Integer id);

    AddressResponseDTO updateAddress(UpdateAddressRequestDTO requestDTO, Integer id);

    void deleteAddressbyId(Integer id);
}
