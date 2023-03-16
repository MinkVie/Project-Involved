package r2s.com.demo.SpringWebDemo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.RoleUserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Product;
import r2s.com.demo.SpringWebDemo.entity.RoleUser;

import java.util.List;

@Component
public class RoleMapper {
    public List<RoleUserResponseDTO> convertEntityToResponseDtos(List<RoleUser> roleList){
        return roleList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public RoleUserResponseDTO convertEntityToResponseDto(RoleUser role) {
        RoleUserResponseDTO roleResponseDTO = new RoleUserResponseDTO();
        BeanUtils.copyProperties( role, roleResponseDTO);
        return roleResponseDTO;
    }
}
