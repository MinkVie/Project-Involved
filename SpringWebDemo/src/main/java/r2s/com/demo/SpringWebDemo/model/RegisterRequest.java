package r2s.com.demo.SpringWebDemo.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String userName;

    private String password;

    private String phone;
}
