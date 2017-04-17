package com.rocket.items.service;

import com.rocket.items.dao.BrandRepository;
import com.rocket.items.dao.CategoryRepository;
import com.rocket.items.dao.ProductRepository;
import com.rocket.items.domain.Brand;
import com.rocket.items.domain.Category;
import com.rocket.items.domain.Page;
import com.rocket.items.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

/**
 * Created by xxu on 4/1/2017.
 */
@Service
public class ItemsService {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public void saveProducts(String productName, String brandName, String categoryName, String pageUrl) {
        Brand brand = brandRepository.findByName(brandName);
        if (brand == null) {
            brand = new Brand();
            brand.setName(brandName);
            brandRepository.save(brand);
        }
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            category = categoryRepository.save(category);
        }
//        Icon icon = new Icon();
//        icon.setUrl("http://");
        Page page = new Page();
        page.setUrl(pageUrl);
        Product product = new Product();
        product.setName(productName);
        product.setBrand(brand);
        product.setCategory(category);
        product.setPage(page);
        productRepository.save(product);
    }
}
