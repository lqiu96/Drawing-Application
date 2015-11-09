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
import java.awt.Paint;

/**
 *
 * @author Lawrence
 */
public class MyText implements MyGraphicsObject {

    private final String text;
    private final int x;
    private final int y;
    private final Paint paint;

    /**
     * Constructor for the MyText object
     *
     * @param text Text to be displayed
     * @param x xPosition of the text
     * @param y yPosition of the text
     * @param paint Color of the text
     */
    public MyText(String text, int x, int y, Paint paint) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor((Color) paint);
        g.drawString(text, x, y);
    }

}
