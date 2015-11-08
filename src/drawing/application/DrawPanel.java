/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import drawing.application.Graphics.MyPolyLine;
import drawing.application.Graphics.MyArc;
import drawing.application.Graphics.MyRectangle;
import drawing.application.Graphics.MyShape;
import drawing.application.Graphics.MyText;
import drawing.application.Graphics.MyBoundedShape;
import drawing.application.Graphics.MyLine;
import drawing.application.Graphics.MyImage;
import drawing.application.Graphics.MyOval;
import drawing.application.Graphics.MyGraphicsObject;
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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays the panel which allows the user to draw on
 *
 * @author Lawrence and Nate
 */
public class DrawPanel extends JPanel {

    //TODO: Make sure that you can change the size on freeform
    private final ArrayList<MyGraphicsObject> shapes;
    private final ArrayList<MyGraphicsObject> redoShapes;
    private int shapeType;
    private MyGraphicsObject currentShape;
    private Paint currentColor;
    private boolean filledShape;
    private final JLabel statusLabel;
    private Stroke currentStroke;
    private String text;
    private boolean eraseSelected;
    private final int eraserSize = 20;
    private final MyOval eraseCursor;

    public DrawPanel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
        shapes = new ArrayList<>(); //No limit on the number of the shapes
        redoShapes = new ArrayList<>();
        this.shapeType = 0;
        this.currentShape = null;
        this.currentColor = Color.BLACK;
        this.currentStroke = new BasicStroke();
        this.text = "";
        this.setBackground(Color.WHITE);
        eraseSelected = false;
        eraseCursor = new MyOval();

        DrawHandler handler = new DrawHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    /**
     * It iterates through the ArrayList of shape objects and draws them. Also
     * draws the current object if it is not null
     *
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        shapes.stream().forEach((shape) -> {    //Utilize Java 8 streams
            shape.draw((Graphics2D) g);
        });
        if (currentShape != null) {
            currentShape.draw((Graphics2D) g);
        }
        if (eraseSelected) {
            eraseCursor.draw((Graphics2D) g);
        }
    }

    /**
     * Sets the Shape type
     *
     * @param shapeType integer value of the shape type
     */
    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    /**
     * Sets the current color
     *
     * @param currentColor Paint object
     */
    public void setCurrentColor(Paint currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * Sets if the shape is filled or not
     *
     * @param filledShape boolean value
     */
    public void setFilledShape(boolean filledShape) {
        this.filledShape = filledShape;
    }

    /**
     * Sets the current stroke
     *
     * @param currentStroke Stroke object
     */
    public void setCurrentStroke(Stroke currentStroke) {
        this.currentStroke = currentStroke;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        shapes.add(new MyImage(image, 0, 0, null));
        repaint();
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Lowers the numbers of shapes in the shapeCount and removes the last shape
     * and adds it into the redoShape repaints the panel
     */
    public void clearLastShape() {
        if (!shapes.isEmpty()) {
            MyGraphicsObject shape = shapes.remove(shapes.size() - 1);
            redoShapes.add(shape);
        }
        repaint();
    }

    /**
     * It removes the last inputted shape into the Array List so that the shape
     * will be displayed back onto the shape
     */
    public void redoLastShape() {
        if (!redoShapes.isEmpty()) {
            MyGraphicsObject shape = redoShapes.remove(redoShapes.size() - 1);
            shapes.add(shape);
        }
        repaint();
    }

    /**
     * Sets the shape count to be 0 and clears the shapes array by removing it
     * from the front first repaints the panel
     */
    public void clearDrawing() {
        while (!shapes.isEmpty()) {
//            It removes from the front because it gets the last shape
//            must be the first to be displayed back first
            MyGraphicsObject shape = shapes.remove(0);
            redoShapes.add(shape);
        }
        repaint();
    }

    /**
     * @return the eraseSelected
     */
    public boolean isErase() {
        return eraseSelected;
    }

    /**
     * @param erase the eraseSelected to set
     */
    public void setErase(boolean erase) {
        this.eraseSelected = erase;
    }

    private class DrawHandler extends MouseAdapter implements MouseMotionListener {

        /**
         * When the user presses down on the panel, it determines which shape to
         * add to the Array List based on the shape type 1 => Line, 2 => Oval, 3
         * => Rectangle If it is an oval or rectangle, it sets if it is filled
         * or not Each shapes gets a beginning point, stroke object, and color
         *
         * @param e MouseEvent
         */
        @Override
        public void mousePressed(MouseEvent e) {
//            Only clear the text message when another shape is selected (e.g when not 5)
            //if the user is not trying to eraseSelected
            if (!eraseSelected) {
                switch (shapeType) {
                    case 0:
                        text = "";
                        currentShape = new MyPolyLine();
                        break;
                    case 1:
                        text = "";
                        currentShape = new MyLine();
                        break;
                    case 2:
                        text = "";
                        currentShape = new MyOval();
                        ((MyBoundedShape) currentShape).setIsFilled(filledShape);
                        break;
                    case 3:
                        text = "";
                        currentShape = new MyRectangle();
                        ((MyBoundedShape) currentShape).setIsFilled(filledShape);
                        break;
                    case 4:
                        text = "";
                        currentShape = new MyArc();
                        ((MyBoundedShape) currentShape).setIsFilled(filledShape);
                        break;
                    case 5:
                        currentShape = new MyText(text, e.getX(), e.getY(), currentColor);
                        break;
                }
                if (currentShape instanceof MyShape) {  //Text isn't part of MyShape Hierarchy
                    ((MyShape) currentShape).setBeginning(new Point(e.getX(), e.getY()));
                    ((MyShape) currentShape).setStroke(currentStroke);
                    ((MyShape) currentShape).setPaint(currentColor);
                }
            } else {
                currentShape = new MyPolyLine();
                //Switching this to setEnd fixes the issue with the eraser drawing from (0,0)
                //Not sure why, but I believe it has to do with polyline drawing with sometimes no points?
                ((MyShape) currentShape).setEnd(new Point(e.getX(), e.getY()));
                ((MyShape) currentShape).setStroke(new BasicStroke(eraserSize, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
                ((MyShape) currentShape).setPaint(getBackground());
            }
        }

        /**
         * When the user lets go on the panel, adds the shape and count. Sets
         * the current shape to be null redraws
         *
         * @param e MouseEvent
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentShape instanceof MyShape) {
                ((MyShape) currentShape).setEnd(new Point(e.getX(), e.getY()));
            }
            shapes.add(currentShape);
            currentShape = null;
            repaint();
        }

        /**
         * When the mouse is moved, it displays the position on the status label
         *
         * @param e MouseEvent
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            statusLabel.setText("(" + e.getX() + ", " + e.getY() + ")");

            if (eraseSelected) {
                eraseCursor.setBeginning(new Point(e.getX() - 10, e.getY() - 10));
                eraseCursor.setEnd(new Point(e.getX() + 10, e.getY() + 10));
                repaint();
            }
        }

        /**
         * When the mouse is dragged on the panel, the current shape (which
         * hasn't been added yet), has its end point constantly changed. It sets
         * the label's text to be the current position redraws the panel
         *
         * @param e MouseEvent
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentShape instanceof MyShape) {
                ((MyShape) currentShape).setEnd(new Point(e.getX(), e.getY()));
            }

            statusLabel.setText("(" + e.getX() + ", " + e.getY() + ")");

            if (eraseSelected) {
                eraseCursor.setBeginning(new Point(e.getX() - 10, e.getY() - 10));
                eraseCursor.setEnd(new Point(e.getX() + 10, e.getY() + 10));
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            eraseCursor.setPaint(Color.BLACK);
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            eraseCursor.setPaint(getBackground());
            repaint();
        }
    }
}
