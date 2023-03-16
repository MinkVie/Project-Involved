package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingCategoryListResponseDTO implements Serializable {
    private List<CategoryResponseDTO> categoryResponseDTOList;

    private Integer size;

    private Integer page;

    private String sort;
}
