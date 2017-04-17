package com.rocket.items.dao;


import com.rocket.items.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xxu on 4/14/2017.
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
