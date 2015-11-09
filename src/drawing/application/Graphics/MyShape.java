/*
 * The MIT License
 *
 * Copyright 2015 Lawrence.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package drawing.application.Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

/**
 * Implements MyGraphicsObject that displays only the 2D shapes Top abstract
 * class in the top of the Shape hierarchy Strokes the beginning and end points,
 * the color, and stroke of the shape
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

    /**
     * Gets the beginning point of the shape
     *
     * @return Beginning Point
     */
    public Point getBeginning() {
        return beginning;
    }

    /**
     * Sets the beginning (x,y) points
     *
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
     *
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

    @Override
    public abstract void draw(Graphics2D g);
}
