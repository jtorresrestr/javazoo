package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.ProductDto;
import org.bootcamp.javazoo.entity.Product;
import org.bootcamp.javazoo.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Override
    public ProductDto mapToProductDto(Product productToMap){
        return new ProductDto(
                productToMap.getId(),
                productToMap.getName(),
                productToMap.getType(),
                productToMap.getBrand(),
                productToMap.getColor(),
                productToMap.getNotes());
    }
}
