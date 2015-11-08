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

/**
 * Inherits from MyBoundedShape and MyShape
 *
 * @author Lawrence and Nate
 */
public class MyRectangle extends MyBoundedShape {

    public MyRectangle() {
        super();
    }

    public MyRectangle(Point beginning, Point end, Color color, Stroke stroke, boolean isFilled) {
        super(beginning, end, color, stroke, isFilled);
    }

    /**
     * Sets the color of the shape and the stroke, and if it is filled Checks to
     * make sure that beginning points are before (less than) end points If not,
     * it displays the shape aiming to the left instead of the right
     *
     * @param g Graphics2D object
     */
    @Override
    public void draw(Graphics2D g) {
        g.setPaint(getPaint());
        g.setStroke(getStroke());
        if (isFilled()) {
            g.fillRect(getBeginning().x > getEnd().x ? getEnd().x : getBeginning().x,
                    getBeginning().y > getEnd().y ? getEnd().y : getBeginning().y,
                    Math.abs(getEnd().x - getBeginning().x),
                    Math.abs(getEnd().y - getBeginning().y));
        } else {
            g.drawRect(getBeginning().x > getEnd().x ? getEnd().x : getBeginning().x,
                    getBeginning().y > getEnd().y ? getEnd().y : getBeginning().y,
                    Math.abs(getEnd().x - getBeginning().x),
                    Math.abs(getEnd().y - getBeginning().y));
        }
    }

}
