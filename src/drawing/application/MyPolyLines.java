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
import java.util.ArrayList;

/**
 *
 * @author pva5115
 */
public class MyPolyLines extends MyShape{

    private ArrayList<Integer> x;
    private ArrayList<Integer> y; 

    public MyPolyLines() {  
        super();
        x = new ArrayList<>();
        y = new ArrayList<>();
    }
    
    public MyPolyLines(Point beginning, Point end, Color color, Stroke stroke) {
        super(beginning, end, color, stroke);
    }
    
    @Override
    public void draw(Graphics2D g) {

        setX();
        setY();
        
        int[] xPoint = new int[x.size()];
        int[] yPoint = new int[y.size()];
        
        for(int i = 0; i < x.size(); i++)
        {
            xPoint[i] = x.get(i);
            yPoint[i] = y.get(i);
        }
        
        g.setPaint(getPaint());
        g.setStroke(getStroke());
        g.drawPolyline(xPoint, yPoint, x.size());
    }
    
    public void setX()
    {
       x.add(getEnd().x);
    }
    
    public void setY(){
        y.add(getEnd().y);
    }
    
}
