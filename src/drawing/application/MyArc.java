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
 *
 * @author Lawrence
 */
public class MyArc extends MyBoundedShape {

    public MyArc() {
        super();
    }
    
    public MyArc(Point beginning, Point end, Color color, Stroke stroke, boolean isFilled) {
        super(beginning, end, color, stroke, isFilled);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setPaint(getPaint());
        g.setStroke(getStroke());
        if (isFilled()) {
            g.fillArc((getBeginning().x < getEnd().x) ? getBeginning().x : getEnd().x,
                    (getBeginning().y < getEnd().y) ? getBeginning().y : getEnd().y,
                    Math.abs(getBeginning().x - getEnd().x),
                    Math.abs(getBeginning().x - getEnd().x), 0, 180);
        } else {
            g.drawArc((getBeginning().x < getEnd().x) ? getBeginning().x : getEnd().x,
                    (getBeginning().y < getEnd().y) ? getBeginning().y : getEnd().y,
                    Math.abs(getBeginning().x - getEnd().x),
                    Math.abs(getBeginning().x - getEnd().x), 0, 180);
        }
    }
    
}
