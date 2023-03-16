package r2s.com.demo.SpringWebDemo.service;

import org.springframework.transaction.annotation.Transactional;
import r2s.com.demo.SpringWebDemo.dto.request.InsertCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CategoryResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategoryDatabase();

    PageResponseDTO getCategoryPaging();

    @Transactional
    Category insertCategory(InsertCategoryRequestDTO requestDTO);

    CategoryResponseDTO getCategorybyId(Integer id);

    @Transactional
    CategoryResponseDTO updateCategory(UpdateCategoryRequestDTO requestDTO, Integer id);

    @Transactional
    void deleteCategorybyId(Integer id);

    CategoryResponseDTO getProductByCategoryId(int categoryId);
}

