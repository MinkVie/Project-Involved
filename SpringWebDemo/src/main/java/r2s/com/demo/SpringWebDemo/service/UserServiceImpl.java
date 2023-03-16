package r2s.com.demo.SpringWebDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import r2s.com.demo.SpringWebDemo.config.JwtTokenUtil;
import r2s.com.demo.SpringWebDemo.dto.request.InsertUserRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateUserRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.AddressResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Address;
import r2s.com.demo.SpringWebDemo.entity.Cart;
import r2s.com.demo.SpringWebDemo.entity.RoleUser;
import r2s.com.demo.SpringWebDemo.entity.User;
import r2s.com.demo.SpringWebDemo.mapper.AddressMapper;
import r2s.com.demo.SpringWebDemo.mapper.RoleMapper;
import r2s.com.demo.SpringWebDemo.mapper.UserMapper;
import r2s.com.demo.SpringWebDemo.model.LoginRequest;
import r2s.com.demo.SpringWebDemo.model.RegisterRequest;
import r2s.com.demo.SpringWebDemo.repository.RoleRepository;
import r2s.com.demo.SpringWebDemo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> getAllUserDatabase() {
        Iterable<User> userIterable = userRepository.findAll();
        return (List<User>) userIterable;
    }

    @Override
    public PageResponseDTO getUserPaging() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<User> userPage = userRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get user by paging"));

        PageResponseDTO pageResponseDTO = new PageResponseDTO();

        pageResponseDTO.setPage(userPage.getNumber());
        pageResponseDTO.setSize(userPage.getSize());
        pageResponseDTO.setTotalPages(userPage.getTotalPages());
        pageResponseDTO.setTotalRecord(userPage.getTotalElements());

        List<UserResponseDTO> userResponseDTOS = userMapper.convertEntityToResponseDtos(userPage.getContent());

        pageResponseDTO.setData(userResponseDTOS);

        return pageResponseDTO;
    }

    @Override
    public UserResponseDTO getUserbyId(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't get user by this id"));
        List<Address> addresses = user.getAddresses();
        List<RoleUser> roleUsers = user.getRoleUsers();
        UserResponseDTO userResponseDTOS = userMapper.convertEntityToResponseDto(user);
        userResponseDTOS.setAddressLists(addressMapper.convertEntityToResponseDtos(addresses));
        userResponseDTOS.setAuthorities(user.getAuthorities());
       // userResponseDTOS.setRoleUserResponseDTOs(roleMapper.convertEntityToResponseDtos(roleUsers));
        return userResponseDTOS;
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(UpdateUserRequestDTO requestDTO, Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't update user by this id"));

        if (findByUserName(requestDTO.getUsername())== null){
            user.setName(requestDTO.getName());
            user.setPhone(requestDTO.getPhone());
            String cypherText = passwordEncoder.encode(requestDTO.getPassword());
            user.setPassword(cypherText);
            user.setBirthday(requestDTO.getBirthday());
            user.setGender(requestDTO.getGender());
            user.setUsername(requestDTO.getUsername());
            user.setEmail(requestDTO.getEmail());

            userRepository.save(user);

            UserResponseDTO userResponseDTO = userMapper.convertEntityToResponseDto(user);

            return userResponseDTO;

        } throw new RuntimeException("Can't update username. Username has been used");


    }

    @Override
    @Transactional
    public void deleteUserbyId(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't delete user by this id"));

        userRepository.delete(user);
    }


    /////////SECURITY--------------------------------------------------------------
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String login(LoginRequest authenticationRequest) {
        User user = findByUserName(authenticationRequest.getUsername());

        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException("User Not Found Exception");
        } else {
            if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
                throw new AuthenticationServiceException("Wrong password");
            }
        }
        return jwtTokenUtil.generateToken(user);
    }

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public UserResponseDTO register(RegisterRequest registerRequest) {
        if (findByUserName(registerRequest.getUserName()) != null) {
            throw new UsernameNotFoundException("Usernam is already exist");
        }
        String password = registerRequest.getPassword();
        String cypherText = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(registerRequest.getUserName());
        user.setPassword(cypherText);
        user.setPhone(registerRequest.getPhone());


        List<RoleUser> roleUsers = new ArrayList<>();
        roleUsers.add(roleService.getRoleUSER());
        user.setRoleUsers(roleUsers);


        //save to Db
        userRepository.save(user);
        //update user list of role USER
        roleService.getListRoleUser(roleService.getRoleUSER());

        UserResponseDTO userResponseDTO = userMapper.convertEntityToResponseDto(user);
        userResponseDTO.setAuthorities(user.getAuthorities());
        return userResponseDTO;
    }

    @Override
    public User findByUserName(String username) {
        User user = getAllUserDatabase()
                .stream()
                .filter(c -> c.getUsername().equals(username))
                .findAny().orElse(null);
        return user;
    }


}
