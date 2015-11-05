/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import javax.swing.JFrame;

/**
 *
 * @author Lawrence
 */
public class DrawingApplication {

    /**
     * Creates a new DrawFrame and displays it with
     * a default size of 800 pixels across and 600 pixels down
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DrawFrame frame = new DrawFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
}
