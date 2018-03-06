package com.opencv.demo;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Detects faces in an image, draws boxes around them, and writes the results
*/
@Component
public class FaceRecognizer {

    private final CascadeClassifier faceDetector;

    public FaceRecognizer() {
        faceDetector = new CascadeClassifier(getPath("lbpcascade_frontalface.xml"));
    }

    public void recognize(Mat image) {
        // Detect faces in the image.
        // MatOfRect is a special container class for Rect.
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
        // Draw a bounding box around each face.
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
    }

    /**
     * Fix for OpenCV bug
     * https://stackoverflow.com/questions/27344741/opencv-3-0-0-facedetect-sample-fails
     */
    private String getPath(String resourceName) {
        return getClass().getResource("/" + resourceName).getPath().substring(1).replaceAll("%20", " ");
    }

}
