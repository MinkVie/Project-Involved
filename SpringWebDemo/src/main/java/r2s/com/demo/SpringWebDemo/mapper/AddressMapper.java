package r2s.com.demo.SpringWebDemo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import r2s.com.demo.SpringWebDemo.dto.response.AddressResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Address;
import r2s.com.demo.SpringWebDemo.entity.User;

import java.util.List;
@Component
public class AddressMapper {
    public List<AddressResponseDTO> convertEntityToResponseDtos(List<Address> addressList){
        return addressList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public AddressResponseDTO convertEntityToResponseDto(Address address) {
        AddressResponseDTO AddressResponseDTO = new AddressResponseDTO();
        BeanUtils.copyProperties( address, AddressResponseDTO);
        return AddressResponseDTO;
    }
}
