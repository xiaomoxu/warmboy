package com.rocket.image;

import html2image4j.ConvertHtmlToImage;
import html2image4j.IHtml2Image;
import net.sf.jni4net.Bridge;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

/**
 * Created by xxu on 3/30/2017.
 */
@SpringBootApplication
@EnableScheduling//run @Schedule marked method
@EnableAsync// run @Async marked method
@EnableEurekaClient
public class ImageApplication {

    @Bean
    @LoadBalanced
    public IHtml2Image iHtml2Image() {
        Bridge.setVerbose(true);
        try {
            String jni4jDLL = ImageApplication.class.getResource("/jni4net.n.w64.v40-0.8.8.0.dll").getPath();
            String html2imageDLL = ImageApplication.class.getResource("/html2image4j.j4n.dll").getPath();
            Bridge.init(new File(jni4jDLL));
            Bridge.LoadAndRegisterAssemblyFrom(new File(html2imageDLL));
            //Bridge.init(new File("C:\\Rocket\\github\\warmboy\\image-service\\src\\main\\resources\\jni4net.n.w64.v40-0.8.8.0.dll"));
            //Bridge.LoadAndRegisterAssemblyFrom(new File("C:\\Rocket\\github\\warmboy\\image-service\\src\\main\\resources\\html2image4j.j4n.dll"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new ConvertHtmlToImage();
    }

    public static void main(String[] args) {
        SpringApplication.run(ImageApplication.class, args);
    }
}
