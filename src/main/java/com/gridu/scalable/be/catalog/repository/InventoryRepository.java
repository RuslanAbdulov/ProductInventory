package com.gridu.scalable.be.catalog.repository;

import com.gridu.scalable.be.catalog.domain.ProductInventory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InventoryRepository {

    private Map<String, ProductInventory> idKeyMap = new HashMap<>();

    public List<ProductInventory> findByIds(List<String> ids) {
        return ids.stream().distinct()
                .map(id -> idKeyMap.get(id))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @PostConstruct
    void loadData() {
        Random random = new Random();
        File file = new File("./src/main/resources/static/jcpenney_com-ecommerce_sample.csv");
        try (CSVParser csvParser = CSVParser.parse(file, Charset.defaultCharset() ,CSVFormat.EXCEL.withHeader())){
            csvParser.iterator().forEachRemaining(record -> {
                ProductInventory product = new ProductInventory(
                        record.get("uniq_id"),
                        random.nextBoolean());

                idKeyMap.put(product.getId(), product);
            });
        } catch (IOException e) {
            System.out.println("Could not parse product data, stop the application.");
            throw new RuntimeException(e);
        }

    }
}
