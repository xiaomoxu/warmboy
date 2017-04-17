package com.rocket.items.controller;

import com.rocket.items.entity.FishingGear;
import com.rocket.items.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xxu on 3/31/2017.
 */
@RestController
public class ItemsController {


    @Autowired
    private ItemsService itemsService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveItems(@RequestBody FishingGear fishingGear) {
        // System.out.println(fishingGear.getWebUrl());
        //fishingGearDao.save(fishingGear);
        itemsService.saveProducts(fishingGear.getName(), fishingGear.getBrandName(), fishingGear.getCategory(), fishingGear.getWebUrl());
    }

//    @RequestMapping(value = "/html2image", method = RequestMethod.GET)
//    public void html2image() {
//        itemsService.createImage();
//    }
}
