package com.studio.loyalty.services.transaction;

import com.studio.loyalty.dtos.ProductDto;
import com.studio.loyalty.dtos.TransactionDto;
import com.studio.loyalty.entities.*;
import com.studio.loyalty.repositories.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.studio.loyalty.utils.LogUtils.LOGGER;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<TransactionEntity> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionEntity getOne(String id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public TransactionEntity save(TransactionDto transaction) throws Exception {
        LOGGER.info(transaction.toString());
        try {
            UserEntity user = userRepository.findById(transaction.getUserid()).orElseThrow(() -> new RuntimeException("User not found"));
            // hitung total harga
            Double total = totalPrice(transaction.getProducts(), user);

            TransactionEntity u = new TransactionEntity();
            u.setQuantity(totalQty(transaction.getProducts()));
            u.setTotal(total);
            u.setEarning(pointCalc(total));
            u.setUser(user);
            u.setProducts(products(transaction.getProducts()));
            // update user points
            user.setPoint(pointUpdate(total, user));
            userRepository.save(user);
            return transactionRepository.save(u);
        } catch (ConstraintViolationException e) {
            throw new SQLException("Data sudah terdaftar");
        }
    }

    @Override
    public TransactionEntity update(TransactionDto transaction, String id) {
        // update belum berhasil
        TransactionEntity u = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        return transactionRepository.save(u);
    }

    @Override
    public Object delete(String id) throws Exception {
        try {
            transactionRepository.deleteById(id);
            return "Berhasil menghapus data";
        } catch (Exception e) {
            throw new SQLException("Data tidak ditemukan");
        }
    }

    private Integer totalQty(Set<ProductDto> products) {
        int total = 0;
        for (ProductDto p : products) {
            total = total + p.getQty();
        }
        return total;
    }

    private ProductEntity getProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    private Double discount(UserEntity user, Double price) {
        if (user.getRank().getRankname().equals(EnumEntity.RANK.SILVER)) {
            return price * 2 / 100;
        } else if (user.getRank().getRankname().equals(EnumEntity.RANK.GOLD)) {
            return price * 5 / 100;
        } else {
            return price * 10 / 100;
        }
    }

    private Double totalPrice(Set<ProductDto> products, UserEntity user) {
        double total = 0;
        for (ProductDto p : products) {
            ProductEntity pe = productRepository.findById(p.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
            total = (total + (p.getQty() * pe.getPrice()) - discount(user, pe.getPrice()));
        }
        return total;
    }

    private Set<ProductEntity> products(Set<ProductDto> products) {
        Set<ProductEntity> pr = new HashSet<>();
        for (ProductDto p : products) {
            ProductEntity pp = getProduct(p.getId());
            pr.add(pp);
        }
        return pr;
    }

    private Integer pointCalc(Double total) {
        double t = total / 10000;
        return (int) t;
    }

    private Integer pointUpdate(Double total, UserEntity user) {
        Integer uPoint = user.getPoint();
        return pointCalc(total) + uPoint;
    }
}
