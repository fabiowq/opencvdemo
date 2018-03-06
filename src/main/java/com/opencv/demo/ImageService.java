package com.opencv.demo;

import org.apache.commons.io.FilenameUtils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImageService {

    private final ImageLoader imageLoader;
    private final FaceRecognizer faceRecognizer;
    private final GrayScale grayScale;
    private final String outputDir;

    ImageService(ImageLoader imageLoader, FaceRecognizer faceRecognizer, GrayScale grayScale, @Value("${output.dir}") String outputDir) {
        this.imageLoader = imageLoader;
        this.faceRecognizer = faceRecognizer;
        this.outputDir = outputDir;
        this.grayScale = grayScale;
    }

    public void process() {
        imageLoader.list().stream().forEach(
            imageFile -> {
                Mat image = Imgcodecs.imread(imageFile.getAbsolutePath());
                faceRecognizer.recognize(image);
                grayScale.rescale(image);
                String filename = FilenameUtils.concat(
                        outputDir,
                        FilenameUtils.getBaseName(imageFile.getName()) + "_" + System.currentTimeMillis() + ".png"
                );
                System.out.println(String.format("Writing %s", filename));
                Imgcodecs.imwrite(filename, image);
            }
        );
    }

}
