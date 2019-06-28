package com.gridu.scalable.be.catalog.api;

import com.gridu.scalable.be.catalog.domain.ProductInventory;
import com.gridu.scalable.be.catalog.manager.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Value("${delay.upper.limit:1000}" )
    private long delayLimit;

    private final InventoryService productService;

    @Autowired
    public InventoryController(InventoryService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductInventory> productsBySku(@RequestParam(name = "uniq_ids") List<String> ids)
            throws InterruptedException {

        Thread.sleep((long) (Math.random() * delayLimit));
        return productService.findByIds(ids);
    }

}
