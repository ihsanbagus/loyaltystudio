package com.studio.loyalty.services.product;

import com.studio.loyalty.dtos.ProductDto;
import com.studio.loyalty.entities.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductEntity> getAll();

    ProductEntity getOne(String id);

    ProductEntity save(ProductDto user) throws Exception;

    ProductEntity update(ProductDto role, String id);

    Object delete(String id) throws Exception;

}
