/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author Lawrence
 */
public class FaceDetection implements Runnable {

    private String filePath;

    public FaceDetection(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        CascadeClassifier faceDetector = new CascadeClassifier(getClass()
                .getResource("lbpcascade_frontalface.xml").getPath());
        Mat image = Imgcodecs.imread(filePath);

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        System.out.println("Detected " + faceDetections.toArray().length + " faces");

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }

        String filename = "faceDetection.png";
        System.out.println(String.format("Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }

}
