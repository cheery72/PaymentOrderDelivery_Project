package com.project.product.service.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.product.domain.product.Product;
import com.project.product.domain.product.ProductStatus;
import com.project.product.dto.ProductRegisterDto;
import com.project.product.repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("물품 등록")
    public void registerProductTest(){
        ProductRegisterDto productRegisterDto = new ProductRegisterDto(1L,"이름",15000,
                                                                "전자기기",List.of("이미지1","이미지2"));
        Product saveProduct = Product.productBuilder(productRegisterDto);

        when(productRepository.save(any()))
                .thenReturn(saveProduct);

        Product newProduct = productService.registerProduct(productRegisterDto);

        assertEquals(1L,newProduct.getSeller());
        assertEquals("이름",newProduct.getName());
        assertEquals(15000,newProduct.getPrice());
        assertEquals("이미지1",newProduct.getImages().get(0).getImage());
        assertEquals(ProductStatus.VERIFICATION,newProduct.getProductStatus());
    }
}