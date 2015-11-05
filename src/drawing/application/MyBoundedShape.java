/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

/**
 * An abstract class which is the parent class for
 * Ovals and Rectangles and the subclass to MyShape
 * 
 * Stores whether it is filled or not
 * @author Lawrence
 */
public abstract class MyBoundedShape extends MyShape {
    private boolean isFilled;
    
    public MyBoundedShape() {
        isFilled = false;
    }
    
    public MyBoundedShape(Point beginning, Point end, Paint paint, Stroke stroke, boolean isFilled) {
        super(beginning, end, paint, stroke);
        this.isFilled = isFilled;
    }

    /**
     * Gets if it is filled
     * @return boolean if is filled
     */
    public boolean isFilled() {
        return isFilled;
    }

    /**
     * Sets if shape is filled
     * @param isFilled boolean if is filled
     */
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }
    
    @Override
    public abstract void draw(Graphics2D g);
    
}
