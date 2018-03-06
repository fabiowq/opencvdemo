package com.opencv.demo;

import org.opencv.core.Core;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    static {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

	public static void main(String[] args) {
	    SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    public CommandLineRunner runner(ImageService imageService) {
        return args -> {
            imageService.process();
        };
    }
}