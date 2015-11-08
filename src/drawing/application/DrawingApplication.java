/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import javax.swing.JFrame;
import org.opencv.core.Core;

/**
 *
 * @author Lawrence and Nate
 */
public class DrawingApplication {

    /**
     * Creates a new DrawFrame and displays it start as full screen with a
     * default size of 800 pixels across and 800 pixels down
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        DrawFrame frame = new DrawFrame();
        frame.setTitle("Drawing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }

}
