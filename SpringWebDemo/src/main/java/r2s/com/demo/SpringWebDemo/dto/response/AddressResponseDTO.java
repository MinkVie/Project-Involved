package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressResponseDTO implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String province;
    private String district;
    private String street;
    private boolean type;
    private boolean defaultAddress;
    private boolean isDeleted;
    private List<UserResponseDTO> user;
    private CartResponseDTO cart;
}
