package com.project.product.service.product;

import com.project.product.domain.product.Product;
import com.project.product.dto.product.ProductRegisterRequest;
import com.project.product.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product registerProduct(ProductRegisterRequest productRegisterRequest){
        Product product = Product.productBuilder(productRegisterRequest);

        return productRepository.save(product);
    }
}
