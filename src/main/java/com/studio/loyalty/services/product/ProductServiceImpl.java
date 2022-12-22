package com.studio.loyalty.services.product;

import com.studio.loyalty.dtos.ProductDto;
import com.studio.loyalty.entities.ProductEntity;
import com.studio.loyalty.repositories.ProductRepository;
import com.studio.loyalty.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getOne(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public ProductEntity save(ProductDto product) throws Exception {
        try {
            ProductEntity u = new ProductEntity();
            u.setProductname(product.getProductname());
            u.setPrice(product.getPrice());
            u.setDescription(product.getDescription());
            u.setImage(product.getImage());
            return productRepository.save(u);
        } catch (Exception e) {
            throw new SQLException("Data sudah terdaftar");
        }
    }

    @Override
    public ProductEntity update(ProductDto product, String id) {
        // update belum berhasil
        ProductEntity u = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productRepository.save(u);
    }

    @Override
    public Object delete(String id) throws Exception {
        try {
            productRepository.deleteById(id);
            return "Berhasil menghapus data";
        } catch (Exception e) {
            throw new SQLException("Data tidak ditemukan");
        }
    }
}
