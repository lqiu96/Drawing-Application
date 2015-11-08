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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author Lawrence and Nate
 */
public class MyPolyLine extends MyShape {

    //Points are stored as Array List so there is no limit on the number of points
    private final ArrayList<Integer> xValues;
    private final ArrayList<Integer> yValues;

    public MyPolyLine() {
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
    }

    public MyPolyLine(Point beginning, Point end, Color color, Stroke stroke) {
        super(beginning, end, color, stroke);
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
    }

    @Override
    public void draw(Graphics2D g) {
        addEndCoordinate(); //Adds the new point before drawing it
        int length = xValues.size();

        int[] xPoint = new int[length]; //g.drawPolyLine requires two int arrays
        int[] yPoint = new int[length];

        IntStream.range(0, length).forEach(i -> {   //Utilize Java 8 streams
            xPoint[i] = xValues.get(i);
            yPoint[i] = yValues.get(i);
        });

        g.setPaint(getPaint());
        g.setStroke(getStroke());
        g.drawPolyline(xPoint, yPoint, length);
    }

    /**
     * Adds the new end point into the Array List of values
     * every time repaint() is called - Moved, Dragged, etc.
     */
    private void addEndCoordinate() {
        xValues.add(getEnd().x);
        yValues.add(getEnd().y);
    }

}
