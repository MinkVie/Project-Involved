package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.service.RoleService;

import java.util.List;

@RestController()
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{role-id}")
    public ResponseEntity<?> getListUserRole(@PathVariable(value = "role-id") int id) {
        List<UserResponseDTO> response = roleService.getListUserOfRole(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{user-id}")
    public ResponseEntity updateUser(@PathVariable(value = "user-id") int userId) {
        UserResponseDTO response = roleService.updateRoleUserToAdmin(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
