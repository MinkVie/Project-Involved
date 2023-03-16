package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import r2s.com.demo.SpringWebDemo.entity.Category;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {
    private int id;
    private String name;
    private long price;
    private String salerName;
    private boolean isDeleted;
}
