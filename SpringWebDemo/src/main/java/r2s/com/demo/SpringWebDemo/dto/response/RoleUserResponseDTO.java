package r2s.com.demo.SpringWebDemo.dto.response;

import java.io.Serializable;
import java.util.List;

public class RoleUserResponseDTO implements Serializable {
    private int id;
    private String roleType;
    private List<UserResponseDTO> userResponseDTOS;
}
