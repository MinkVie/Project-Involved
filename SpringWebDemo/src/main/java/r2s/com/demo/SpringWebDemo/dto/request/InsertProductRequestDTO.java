package r2s.com.demo.SpringWebDemo.dto.request;

import lombok.Data;
import r2s.com.demo.SpringWebDemo.entity.Category;

@Data
public class InsertProductRequestDTO {
    private String name;
    private long price;
    private String salerName;
    private boolean isDeleted;
    private Category category;
}
