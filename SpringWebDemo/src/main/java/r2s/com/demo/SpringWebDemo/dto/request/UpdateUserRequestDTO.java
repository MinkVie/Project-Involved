package r2s.com.demo.SpringWebDemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO implements Serializable {
    private int id;
    private String username;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private String email;
    private Date birthday;

}
