package com.gridu.scalable.be.catalog.manager;

import com.gridu.scalable.be.catalog.domain.ProductInventory;
import com.gridu.scalable.be.catalog.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository productRepository;

    @Autowired
    public InventoryService(InventoryRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductInventory> findByIds(List<String> ids) {
        return productRepository.findByIds(ids);
    }

}
