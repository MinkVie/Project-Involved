package r2s.com.demo.SpringWebDemo.service;

import r2s.com.demo.SpringWebDemo.dto.request.InsertProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductDatabase();

    PageResponseDTO getProductPaging();

    Product insertProduct(InsertProductRequestDTO requestDTO);

    ProductResponseDTO getProductbyId(Integer id);

    ProductResponseDTO updateProduct(UpdateProductRequestDTO requestDTO, Integer id);

    void deleteProductbyId(Integer id);
}
