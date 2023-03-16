package r2s.com.demo.SpringWebDemo.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.User;

import java.util.List;
@Component
public class UserMapper {
    public List<UserResponseDTO> convertEntityToResponseDtos(List<User> userList){
        return userList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public UserResponseDTO convertEntityToResponseDto(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties( user, userResponseDTO);
        return userResponseDTO;
    }
}
