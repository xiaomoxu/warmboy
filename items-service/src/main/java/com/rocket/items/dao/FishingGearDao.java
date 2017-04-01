package com.rocket.items.dao;

import com.rocket.items.entity.FishingGear;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by xxu on 3/30/2017.
 */
@Transactional
public interface FishingGearDao extends CrudRepository<FishingGear, Long> {

}
