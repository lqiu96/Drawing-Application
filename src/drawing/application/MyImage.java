/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author Lawrence and Nate
 */
public class MyImage implements MyGraphicsObject {
    private final BufferedImage image;
    private final int x;
    private final int y;
    private final ImageObserver observer;

    public MyImage(BufferedImage image, int x, int y, ImageObserver observer) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.observer = observer;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, observer);
    }
    
}
