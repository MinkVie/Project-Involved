package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import r2s.com.demo.SpringWebDemo.entity.Address;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {
    private int id;
    private String username;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private String email;
    private Date birthday;
    private List<AddressResponseDTO> addressLists;
    private List<GrantedAuthority> authorities;

}
