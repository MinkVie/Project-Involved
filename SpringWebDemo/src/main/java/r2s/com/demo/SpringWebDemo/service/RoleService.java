package r2s.com.demo.SpringWebDemo.service;

import org.springframework.security.core.GrantedAuthority;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.RoleUser;
import r2s.com.demo.SpringWebDemo.entity.User;

import java.util.List;

public interface RoleService {


   // List<String> getRoleTypesByUserId(int userId);

    UserResponseDTO updateRoleUserToAdmin(int userId);

    void getListRoleUser(RoleUser roleUser);

    void getListRoleAdmin(User user);

    RoleUser getRoleADMIN();

    RoleUser getRoleUSER();

    boolean checkUserIsAdmin(User user);


    List<UserResponseDTO> getListUserOfRole(int id);

    List<GrantedAuthority> getAuthoritiesOfUser(User user);
}
