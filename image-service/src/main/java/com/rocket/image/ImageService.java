package com.rocket.image;

import html2image4j.ConvertHtmlToImage;
import html2image4j.IHtml2Image;
import net.sf.jni4net.Bridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


/**
 * Created by xxu on 4/1/2017.
 */
@Service
public class ImageService {

    @Autowired
    private IHtml2Image iHtml2Image;

    public void createImage() {
        /* FishingGear gear = fishingGearDao.findOne((long) 1); */
        if (iHtml2Image != null) {
            iHtml2Image.ConvertHtmlToImage("http://www.daiwa.com/jp/fishing/item3/hari/ito/index.html", 800, "C:\\Rocket\\test.png");
        }
    }
}



