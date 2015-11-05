/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

/**
 * Inherits from MyBoundedShape and MyShape
 * @author Lawrence
 */
public class MyOval extends MyBoundedShape {

    public MyOval() {
        super();
    }
    
    public MyOval(Point beginning, Point end, Color color, Stroke stroke, boolean isFilled) {
        super(beginning, end, color, stroke, isFilled);
    }
    
    /**
     * Sets the color of the shape and the stroke, and if it is filled
     * Checks to make sure that beginning points are before (less than) end points
     * If not, it displays the shape aiming to the left instead of the right
     * @param g Graphics2D object
     */
    @Override
    public void draw(Graphics2D g) {
        g.setPaint(getPaint());
        g.setStroke(getStroke());
        if (isFilled()) {
            g.fillOval(getBeginning().x > getEnd().x ? getEnd().x : getBeginning().x,
                    getBeginning().y > getEnd().y ? getEnd().y : getBeginning().y,
                    Math.abs(getEnd().x - getBeginning().x),
                Math.abs(getEnd().y - getBeginning().y));
        } else {
            g.drawOval(getBeginning().x > getEnd().x ? getEnd().x : getBeginning().x,
                    getBeginning().y > getEnd().y ? getEnd().y : getBeginning().y,
                    Math.abs(getEnd().x - getBeginning().x),
                Math.abs(getEnd().y - getBeginning().y));
        }
    }
    
}
