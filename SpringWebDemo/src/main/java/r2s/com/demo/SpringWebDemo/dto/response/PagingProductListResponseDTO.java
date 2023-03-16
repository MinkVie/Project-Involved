package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingProductListResponseDTO implements Serializable {
    private List<ProductResponseDTO> productResponseDTOList;

    private Integer size;

    private Integer page;

    private String sort;
}
