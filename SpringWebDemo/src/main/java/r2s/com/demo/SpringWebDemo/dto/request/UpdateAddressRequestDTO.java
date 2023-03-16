package r2s.com.demo.SpringWebDemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateAddressRequestDTO implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String province;
    private String district;
    private String street;
    private boolean type;
    private boolean defaultAddress;
    private boolean isDeleted;
}
