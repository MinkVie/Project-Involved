package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingCartListResponseDTO implements Serializable {
    private List<CartResponseDTO> cartResponseDTOList;

    private Integer size;

    private Integer page;

    private String sort;
}
