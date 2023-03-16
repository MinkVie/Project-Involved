package r2s.com.demo.SpringWebDemo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Product;

import java.util.List;
@Component
public class ProductMapper {
    public List<ProductResponseDTO> convertEntityToResponseDtos(List<Product> productList){
        return productList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public ProductResponseDTO convertEntityToResponseDto(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties( product, productResponseDTO);
        return productResponseDTO;
    }
}
