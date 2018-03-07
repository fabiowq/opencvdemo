package com.opencv.demo;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class GrayScale {

    public void rescale(Mat image) {
        Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2GRAY);
        Imgproc.threshold(image, image, 100,255, Imgproc.THRESH_BINARY);
    }

}
