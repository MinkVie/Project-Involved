package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.InsertProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Product;
import r2s.com.demo.SpringWebDemo.service.ProductService;

import java.util.List;

@RestController()
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProductPaging() {
        PageResponseDTO pageResponseDTO = productService.getProductPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "product-id") int productId) {
        ProductResponseDTO ProductResponseDTO = productService.getProductbyId(productId);
        return new ResponseEntity<>(ProductResponseDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertProduct(@RequestBody InsertProductRequestDTO requestDTO) {
        Product product = productService.insertProduct(requestDTO);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PutMapping("/{product-id}")
    public ResponseEntity updateProduct(@PathVariable(value = "product-id") int productId,
                                     @RequestBody UpdateProductRequestDTO updateProductRequestDTO) {
        ProductResponseDTO response = productService.updateProduct(updateProductRequestDTO, productId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/{product-id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "product-id") int productId) {
        productService.deleteProductbyId(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
