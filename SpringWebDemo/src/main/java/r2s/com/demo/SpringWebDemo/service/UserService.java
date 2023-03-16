package r2s.com.demo.SpringWebDemo.service;

import r2s.com.demo.SpringWebDemo.dto.request.InsertUserRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateUserRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.User;
import r2s.com.demo.SpringWebDemo.model.LoginRequest;
import r2s.com.demo.SpringWebDemo.model.RegisterRequest;

import java.util.List;

public interface UserService {
    List<User> getAllUserDatabase();

    PageResponseDTO getUserPaging();


    UserResponseDTO getUserbyId(Integer id);


    UserResponseDTO updateUser(UpdateUserRequestDTO requestDTO, Integer id);

    void deleteUserbyId(Integer id);

    String login(LoginRequest authenticationRequest);

    UserResponseDTO register(RegisterRequest registerRequest) throws Exception;

    User findByUserName(String username);

}
