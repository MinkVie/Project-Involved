package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingAdsressListResponseDTO implements Serializable {
    private List<AddressResponseDTO> addressResponseDTOList;

    private Integer size;

    private Integer page;

    private String sort;
}
