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
 * Inherits from MyShape
 *
 * @author Lawrence and Nate
 */
public class MyLine extends MyShape {

    public MyLine() {
        super();
    }

    public MyLine(Point beginning, Point end, Color color, Stroke stroke) {
        super(beginning, end, color, stroke);
    }

    /**
     * Sets the color of the line, the stroke of the line Draws the line from
     * the beginning (x,y) position to the end (x,y)
     *
     * @param g
     */
    @Override
    public void draw(Graphics2D g) {
        g.setPaint(getPaint());
        g.setStroke(getStroke());
        g.drawLine(getBeginning().x, getBeginning().y, getEnd().x, getEnd().y);
    }

}
