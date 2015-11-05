/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays the panel which allows the user to draw on
 * 
 * @author Lawrence
 */
public class DrawPanel extends JPanel {
    private final ArrayList<MyShape> shapes;
    private final ArrayList<MyShape> redoShapes;
    private int shapeType;
    private MyShape currentShape;
    private Paint currentColor;
    private boolean filledShape;
    private final JLabel statusLabel;
    private Stroke currentStroke;
    
    public DrawPanel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
//        ArrayList so that there is no limit on the number of shapes
        shapes = new ArrayList<>();
        redoShapes = new ArrayList<>();
        this.shapeType = 0;
        this.currentShape = null;
        this.currentColor = Color.BLACK;
        this.currentStroke = new BasicStroke();
        this.setBackground(Color.WHITE);
        DrawHandler handler = new DrawHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }
    
    /**
     * It iterates through the ArrayList of shape objects
     * and draws them. Also draws the current object if it is not null
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw((Graphics2D) g);
        }
        if (currentShape != null) {
            currentShape.draw((Graphics2D) g);
        }
    }

    /**
     * Sets the Shape type
     * @param shapeType integer value of the shape type
     */
    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    /**
     * Sets the current color
     * @param currentColor Paint object
     */
    public void setCurrentColor(Paint currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * Sets if the shape is filled or not
     * @param filledShape boolean value
     */
    public void setFilledShape(boolean filledShape) {
        this.filledShape = filledShape;
    }

    /**
     * Sets the current stroke
     * @param currentStroke Stroke object
     */
    public void setCurrentStroke(Stroke currentStroke) {
        this.currentStroke = currentStroke;
    }
    
    /**
     * Lowers the numbers of shapes in the shapeCount
     * and removes the last shape and adds it into
     * the redoShape
     * repaints the panel
     */
    public void clearLastShape() {
        if (!shapes.isEmpty()) {
            MyShape shape = shapes.remove(shapes.size() - 1);
            redoShapes.add(shape);
        }
        repaint();
    }
    
    /**
     * It removes the last inputted shape into the Array List so that
     * the shape will be displayed back onto the shape
     */
    public void redoLastShape() {
        if (!redoShapes.isEmpty()) {
            MyShape shape = redoShapes.remove(redoShapes.size() - 1);
            shapes.add(shape);
        }
        repaint();
    }
    
    /**
     * Sets the shape count to be 0 and clears the shapes array
     * by removing it from the front first
     * repaints the panel
     */
    public void clearDrawing() {
        while (!shapes.isEmpty()) {
//            It removes from the front because it gets the last shape
//            must be the first to be displayed back first
            MyShape shape = shapes.remove(0);
            redoShapes.add(shape);
        }
        repaint();
    }
    
    private class DrawHandler extends MouseAdapter implements MouseMotionListener {
        /**
         * When the user presses down on the panel, it determines which
         * shape to add to the Array List based on the shape type
         * 1 => Line, 2 => Oval, 3 => Rectangle
         * If it is an oval or rectangle, it sets if it is filled or not
         * Each shapes gets a beginning point, stroke object, and color
         * @param e MouseEvent
         */
        @Override
        public void mousePressed(MouseEvent e) {
            switch (shapeType) {
                case 0:
                    currentShape = new MyLine();
                    break;
                case 1:
                    currentShape = new MyOval();
                    ((MyBoundedShape) currentShape).setIsFilled(filledShape);
                    break;
                case 2:
                    currentShape = new MyRectangle();
                    ((MyBoundedShape) currentShape).setIsFilled(filledShape);
                    break;
            }
            currentShape.setBeginning(new Point(e.getX(), e.getY()));
            currentShape.setStroke(currentStroke);
            currentShape.setPaint(currentColor);
        }
        
        /**
         * When the user lets go on the panel, adds the shape and count.
         * Sets the current shape to be null
         * redraws
         * @param e MouseEvent
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            shapes.add(currentShape);
            currentShape = null;
            repaint();
        }
        
        /**
         * When the mouse is moved, it displays the position on the status label
         * @param e MouseEvent
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            statusLabel.setText("(" + e.getX() + ", " + e.getY() + ")");
        }
        
        /**
         * When the mouse is dragged on the panel, the current shape
         * (which hasn't been added yet), has its end point constantly changed.
         * It sets the label's text to be the current position
         * redraws the panel
         * @param e MouseEvent
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            currentShape.setEnd(new Point(e.getX(), e.getY()));
            statusLabel.setText("(" + e.getX() + ", " + e.getY() + ")");
            repaint();
        }
    }
}
