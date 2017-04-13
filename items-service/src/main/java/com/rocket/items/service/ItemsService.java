package com.rocket.items.service;

import com.rocket.items.dao.FishingGearDao;
import com.rocket.items.entity.FishingGear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xxu on 4/1/2017.
 */
@Service
public class ItemsService {

    @Autowired
    private FishingGearDao fishingGearDao;

    public void createImage() {
        FishingGear gear = fishingGearDao.findOne((long) 1);
    }
}
