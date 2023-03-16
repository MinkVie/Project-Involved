package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.config.JwtTokenUtil;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.User;
import r2s.com.demo.SpringWebDemo.model.JwtResponse;
import r2s.com.demo.SpringWebDemo.model.LoginRequest;
import r2s.com.demo.SpringWebDemo.model.RegisterRequest;
import r2s.com.demo.SpringWebDemo.service.UserService;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest)
            throws Exception {

        String token = userService.login(authenticationRequest);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws Exception {
        UserResponseDTO user = userService.register(registerRequest);
        return ResponseEntity.ok(user);
    }

}