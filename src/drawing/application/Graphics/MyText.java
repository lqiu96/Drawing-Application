/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
