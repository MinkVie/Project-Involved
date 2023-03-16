package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO implements Serializable {
    private int id;
    private int userId;
    private AddressResponseDTO addressResponseDTO;
}
