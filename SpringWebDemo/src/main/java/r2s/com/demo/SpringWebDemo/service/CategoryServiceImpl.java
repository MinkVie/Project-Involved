package r2s.com.demo.SpringWebDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import r2s.com.demo.SpringWebDemo.dto.request.InsertCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CategoryResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PageResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Category;
import r2s.com.demo.SpringWebDemo.entity.Product;
import r2s.com.demo.SpringWebDemo.mapper.CategoryMapper;
import r2s.com.demo.SpringWebDemo.mapper.ProductMapper;
import r2s.com.demo.SpringWebDemo.repository.CategoryRepository;

import java.util.List;
@Component
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Category> getAllCategoryDatabase() {
        Iterable<Category> categoryIterable = categoryRepository.findAll();
        return (List<Category>) categoryIterable;
    }
    @Override
    public PageResponseDTO getCategoryPaging() {
        Pageable pageable = PageRequest.of(0,2);
        Page<Category> categoryPage = categoryRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get category by paging"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(categoryPage.getNumber());
        pageResponseDTO.setSize(categoryPage.getSize());
        pageResponseDTO.setTotalPages(categoryPage.getTotalPages());
        pageResponseDTO.setTotalRecord(categoryPage.getTotalElements());

        List<CategoryResponseDTO> categoryResponseDTOS = categoryMapper.convertEntityToResponseDtos(categoryPage.getContent());
        pageResponseDTO.setData(categoryResponseDTOS);
        return pageResponseDTO;
    }

    @Override
    @Transactional
    public Category insertCategory(InsertCategoryRequestDTO requestDTO) {
        Category category= new Category();
        category.setName(requestDTO.getName());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public CategoryResponseDTO getCategorybyId(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't get category by this id"));

        CategoryResponseDTO categoryResponseDTOS = categoryMapper.convertEntityToResponseDto(category);
        return categoryResponseDTOS;
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(UpdateCategoryRequestDTO requestDTO, Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't update category by this id"));
        category.setName(requestDTO.getName());
        categoryRepository.save(category);
        CategoryResponseDTO categoryResponseDTO = categoryMapper.convertEntityToResponseDto(category);
        return categoryResponseDTO;
    }

    @Override
    @Transactional
    public void deleteCategorybyId(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't delete category by this id"));

        categoryRepository.delete(category);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getProductByCategoryId(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Can't delete us by this id"));

        List<Product> productList = category.getProductList();

        List<ProductResponseDTO> productResponseDTOList = productMapper.convertEntityToResponseDtos(productList);
        CategoryResponseDTO categoryResponseDTO= categoryMapper.convertEntityToResponseDto(category);
        categoryResponseDTO.setProductResponseDTOList(productResponseDTOList);

        return categoryResponseDTO;
    }

}
