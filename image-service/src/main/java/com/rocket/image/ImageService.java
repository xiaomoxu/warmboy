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

        public void createImage() {
        /* FishingGear gear = fishingGearDao.findOne((long) 1); */
            try {
                Bridge.setVerbose(true);
                Bridge.init(new File("C:\\Rocket\\github\\warmboy\\image-service\\src\\main\\resources\\jni4net.n.w64.v40-0.8.8.0.dll"));
                Bridge.LoadAndRegisterAssemblyFrom(new File("C:\\Rocket\\github\\warmboy\\image-service\\src\\main\\resources\\html2image4j.j4n.dll"));
                IHtml2Image convert = new ConvertHtmlToImage();
                convert.ConvertHtmlToImage("http://www.daiwa.com/jp/fishing/item3/hari/ito/index.html", 800, "C:\\Rocket\\test.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void main(String argz[]){
            ImageService service = new ImageService();
            service.createImage();
        }
    }



