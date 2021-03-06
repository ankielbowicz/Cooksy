package com.cooksy.service;

import com.cooksy.dto.ProductDto;
import com.cooksy.model.Product;
import com.cooksy.repository.ProductRepository;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import com.cooksy.util.converter.ProductToProductDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductToProductDtoConverter productToProductDtoConverter;
    private final ProductDtoToProductConverter productDtoToProductConverter;


    public List<ProductDto> getAll() {
        return productToProductDtoConverter.convertAll((List<Product>) productRepository.findAll());
    }

    public void saveProduct(ProductDto productDto) {
        productRepository.save(productDtoToProductConverter.convert(productDto));
    }

    public ProductDto getProductByID(Long id) {
        if (productRepository.findById(id).isPresent()) {
            return productToProductDtoConverter.convert(productRepository.findById(id).get());
        }
        return null;  //??
    }

    public void deleteProduct(ProductDto productDto) {
        productRepository.delete(productDtoToProductConverter.convert(productDto));
    }
}
