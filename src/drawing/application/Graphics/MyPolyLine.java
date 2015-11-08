/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;

/**
 *
 * @author pva5115
 */
public class MyPolyLine extends MyShape {

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
        addEndCoordinate();

        int length = xValues.size();

        int[] xPoint = new int[length];
        int[] yPoint = new int[length];

        for (int i = 0; i < length; i++) {
            xPoint[i] = xValues.get(i);
            yPoint[i] = yValues.get(i);
        }

        g.setPaint(getPaint());
        g.setStroke(getStroke());
        g.drawPolyline(xPoint, yPoint, length);
    }

    private void addEndCoordinate() {
        xValues.add(getEnd().x);
        yValues.add(getEnd().y);
    }

}
