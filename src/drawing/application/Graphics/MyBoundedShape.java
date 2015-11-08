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

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

/**
 * An abstract class which is the parent class for Ovals and Rectangles and the
 * subclass to MyShape Stores whether it is filled or not
 *
 * @author Lawrence and Nate
 */
public abstract class MyBoundedShape extends MyShape {

    private boolean isFilled;   //Determines if the shape (Rectangle, Oval, Arc) is filled

    public MyBoundedShape() {
        isFilled = false;
    }

    public MyBoundedShape(Point beginning, Point end, Paint paint, Stroke stroke, boolean isFilled) {
        super(beginning, end, paint, stroke);
        this.isFilled = isFilled;
    }

    /**
     * Gets if it is filled
     *
     * @return boolean if is filled
     */
    public boolean isFilled() {
        return isFilled;
    }

    /**
     * Sets if shape is filled
     *
     * @param isFilled boolean if is filled
     */
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    @Override
    public abstract void draw(Graphics2D g);

}
