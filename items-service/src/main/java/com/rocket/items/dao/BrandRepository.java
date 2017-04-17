package com.rocket.items.dao;

import com.rocket.items.domain.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xxu on 4/17/2017.
 */
@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
    public Brand findByName(@Param("name") String name);
}
