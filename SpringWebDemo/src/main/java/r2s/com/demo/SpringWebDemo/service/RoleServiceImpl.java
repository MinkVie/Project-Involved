package r2s.com.demo.SpringWebDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.RoleUser;
import r2s.com.demo.SpringWebDemo.entity.User;
import r2s.com.demo.SpringWebDemo.mapper.RoleMapper;
import r2s.com.demo.SpringWebDemo.mapper.UserMapper;
import r2s.com.demo.SpringWebDemo.repository.RoleRepository;
import r2s.com.demo.SpringWebDemo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserResponseDTO updateRoleUserToAdmin(int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Can't find user by this user id"));

        if (checkUserIsAdmin(user)) {
            System.out.println("User account was ADMIN");
            UserResponseDTO userResponseDTO = userMapper.convertEntityToResponseDto(user);
            userResponseDTO.setAuthorities(user.getAuthorities());
            return userResponseDTO;
        }

        List<RoleUser> roleUsers = user.getRoleUsers();
        roleUsers.add(getRoleADMIN());
        user.setRoleUsers(roleUsers);

        userRepository.save(user);
        getListRoleAdmin(user);
        UserResponseDTO userResponseDTO = userMapper.convertEntityToResponseDto(user);
        userResponseDTO.setAuthorities(user.getAuthorities());
        return userResponseDTO;
    }

    @Override
    public void getListRoleUser(RoleUser roleUser) {
        roleUser.setUsers(userService.getAllUserDatabase());
        roleRepository.save(roleUser);
    }

    @Override
    public void getListRoleAdmin(User user) {
        List<User> users = getRoleADMIN().getUsers();
        users.add(user);
        getRoleADMIN().setUsers(users);
        roleRepository.save(getRoleADMIN());
    }

    @Override
    public RoleUser getRoleADMIN() {
        RoleUser roleUser = roleRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Not exist role ADMIN"));
        return roleUser;
    }

    @Override
    public RoleUser getRoleUSER() {
        RoleUser roleUser = roleRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Not exist role USER"));
        return roleUser;
    }

    @Override
    public boolean checkUserIsAdmin(User user) {
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserResponseDTO> getListUserOfRole(int id) {
        if (id == 1) {
            List<User> users = getRoleUSER().getUsers();
            List<UserResponseDTO> userResponseDTOs = userMapper.convertEntityToResponseDtos(users);
            return userResponseDTOs;
        } else if (id == 2) {
            List<User> users = getRoleADMIN().getUsers();
            List<UserResponseDTO> userResponseDTOs = userMapper.convertEntityToResponseDtos(users);
            return userResponseDTOs;
        }
        return null;
    }

    @Override
    public List<GrantedAuthority> getAuthoritiesOfUser(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        List<String> roleTypes = new ArrayList<>();
        User user1 = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Fail!"));
        for (RoleUser roleUser : user1.getRoleUsers()) {
            String roleType = roleUser.getRoleType();
            grantedAuthorities.add(new SimpleGrantedAuthority(roleType));
        }
        return grantedAuthorities;
    }


}
