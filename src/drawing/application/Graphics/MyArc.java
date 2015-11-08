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
 *
 * @author Lawrence and Nate
 */
public class MyArc extends MyBoundedShape {

    public MyArc() {
        super();
    }

    public MyArc(Point beginning, Point end, Color color, Stroke stroke, boolean isFilled) {
        super(beginning, end, color, stroke, isFilled);
    }

    /**
     * Essentially draws the arc from Point 1 to Point 2 (Beginning to End)
     * and determines the angle from and to (If flat, it should just be 0 - 180)
     * 
     * @param g Graphics2D object
     */
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
