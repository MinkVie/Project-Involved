package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.InsertCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CategoryResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CategoryResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CategoryResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Category;
import r2s.com.demo.SpringWebDemo.service.CategoryService;

import java.util.List;

@RestController()
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<?> getCategoryPaging() {
        PageResponseDTO pageResponseDTO = categoryService.getCategoryPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "category-id") int categoryId) {
        CategoryResponseDTO CategoryResponseDTO = categoryService.getCategorybyId(categoryId);
        return new ResponseEntity<>(CategoryResponseDTO, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity insertCategory(@RequestBody InsertCategoryRequestDTO requestDTO) {
        Category category = categoryService.insertCategory(requestDTO);
        return new ResponseEntity(category, HttpStatus.OK);
    }


    @PutMapping("/{category-id}")
    public ResponseEntity updateCategory(@PathVariable(value = "category-id") int categoryId,
                                     @RequestBody UpdateCategoryRequestDTO updateCategoryRequestDTO) {
        CategoryResponseDTO response = categoryService.updateCategory(updateCategoryRequestDTO, categoryId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/{category-id}")
    public ResponseEntity deleteCategory(@PathVariable(value = "category-id") int categoryId) {
        categoryService.deleteCategorybyId(categoryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //
    @GetMapping("/{category-id}/product")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable(value = "category-id") int categoryId) {
        CategoryResponseDTO categoryResponseDTO = categoryService.getProductByCategoryId(categoryId);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

}
