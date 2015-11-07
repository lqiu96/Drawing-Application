/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import java.awt.Graphics2D;

/**
 *
 * @author Lawrence and Nate
 */
public interface MyGraphicsObject {

    /**
     * Abstract draw method which each non-abstract shape class must determine
     * how to draw and display on the panel.
     *
     * @param g Graphics2D object
     */
    public void draw(Graphics2D g);
}
