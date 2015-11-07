/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

/**
 * Implements MyGraphicsObject that displays only the 2D shapes
 * Top abstract class in the top of the Shape hierarchy
 * Strokes the beginning and end points, the color, and stroke of the shape
 * 
 * @author Lawrence and Nate
 */
public abstract class MyShape implements MyGraphicsObject {
    private Point beginning;
    private Point end;
    private Paint paint;
    private Stroke stroke;
    
    public MyShape() {
        beginning = new Point(0, 0);
        end = new Point(0, 0);
        paint = Color.BLACK;
        stroke = new BasicStroke();
    }

    public MyShape(Point beginning, Point end, Paint paint, Stroke stroke) {
        this.beginning = beginning;
        this.end = end;
        this.paint = paint;
        this.stroke = stroke;
    }

    public Point getBeginning() {
        return beginning;
    }

    /**
     * Sets the beginning (x,y) points
     * @param beginning Point object
     */
    public void setBeginning(Point beginning) {
        this.beginning = beginning;
    }

    /**
     * Gets the end (x,y) points
     * 
     * @return Point object
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Sets the end (x,y) points
     * 
     * @param end Point object
     */
    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * Gets the color of the shape
     * @return Paint object
     */
    public Paint getPaint() {
        return paint;
    }

    /**
     * Sets the color of the shape
     * 
     * @param paint Paint object
     */
    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    /**
     * Get the stroke of the shape
     * 
     * @return Stroke object
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * Sets the stroke of the shape
     * 
     * @param stroke Stroke object
     */
    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
    
    public abstract void draw(Graphics2D g);
}
