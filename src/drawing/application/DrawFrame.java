/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.application;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Displays the panel which allows the user to choose
 * options from button and the panel to draw on
 * 
 * @author Lawrence
 */
public class DrawFrame extends JFrame {
    private final JButton undo;
    private final JButton clear;
    private final JComboBox<String> colors;
    private final JComboBox<String> shapes;
    private final JCheckBox filled;
    private final JLabel statusLabel;
    private final DrawPanel panel;
    
    private final JCheckBox gradient;
    private final JButton firstColor;
    private final JButton secondColor;
    private final JLabel strokeWidthLabel;
    private final JTextField strokeWidth;
    private final JLabel strokeDashLengthLabel;
    private final JTextField strokeDashLength;
    private final JCheckBox dashed;
    
    private boolean isGradient;
    private Color color1;
    private Color color2;
    private int lineWidth;
    private int dashWidth;
    private boolean isDashed;
    
    //Just choose a couple of the default colors -- Can add more
    private final String[] colorOptions = {"Black", "Red", "Green", "Blue",
        "Orange", "Yellow"};
    private final String[] shapeOptions = {"Line", "Oval", "Rectangle"};
    
    public DrawFrame() {
        isGradient = false;
        color1 = Color.BLACK;
        color2 = Color.BLACK;
        lineWidth = 1;
        dashWidth = 0;
        isDashed = false;
        
        undo = new JButton("Undo");
        UndoHandler undoListener = new UndoHandler();
        undo.addActionListener(undoListener);
        clear = new JButton("Clear");
        ClearHandler clearListener = new ClearHandler();
        clear.addActionListener(clearListener);
        colors = new JComboBox(colorOptions);
        ColorHandler colorListener = new ColorHandler();
        colors.addItemListener(colorListener);
        shapes = new JComboBox(shapeOptions);
        ShapeHandler shapeListener = new ShapeHandler();
        shapes.addItemListener(shapeListener);
        FilledHandler filledListener = new FilledHandler();
        filled = new JCheckBox("Filled");
        filled.addActionListener(filledListener);
        
        gradient = new JCheckBox("Use Gradient");
        GradientHandler gradientListener = new GradientHandler();
        gradient.addActionListener(gradientListener);
        FirstColorHandler firstColorListener = new FirstColorHandler();
        firstColor = new JButton("1st Color...");
        firstColor.addActionListener(firstColorListener);
        SecondColorHandler secondColorListener = new SecondColorHandler();
        secondColor = new JButton("2nd Color...");
        secondColor.addActionListener(secondColorListener);
        strokeWidthLabel = new JLabel("Line Width:");
        strokeWidth = new JTextField("", 3);
        //Create a new document listener because an action listener
        //would require the user to press enter to register
        strokeWidth.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    lineWidth = Integer.parseInt(strokeWidth.getText());
                } catch (NumberFormatException ex) {
                    lineWidth = 1;
                }
                if (isDashed) {
                    float[] dashes = {dashWidth};
                    panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND, 10, dashes, 0));  
                } else {
                    panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));       
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    lineWidth = Integer.parseInt(strokeWidth.getText());
                } catch (NumberFormatException ex) {
                    lineWidth = 1;
                }
                if (isDashed) {
                    float[] dashes = {dashWidth};
                    panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND, 10, dashes, 0));  
                } else {
                    panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));       
                }            
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    lineWidth = Integer.parseInt(strokeWidth.getText());
                } catch (NumberFormatException ex) {
                    lineWidth = 1;
                }
                if (isDashed) {
                    float[] dashes = {dashWidth};
                    panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND, 10, dashes, 0));  
                } else {
                    panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));       
                }            
            }
            
        });
        strokeDashLengthLabel = new JLabel("Dash Length:");
        strokeDashLength = new JTextField("", 3);
        //Create a new document listener because an action listener
        //would require the user to press enter to register
        strokeDashLength.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    dashWidth = Integer.parseInt(strokeDashLength.getText());
                } catch (NumberFormatException ex) {
                    dashWidth = 1;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    dashWidth = Integer.parseInt(strokeDashLength.getText());
                } catch (NumberFormatException ex) {
                    dashWidth = 1;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    dashWidth = Integer.parseInt(strokeDashLength.getText());
                } catch (NumberFormatException ex) {
                    dashWidth = 1;
                }
            }
            
        });
        DashedHandler dashListener = new DashedHandler();
        dashed = new JCheckBox("Dashed");
        dashed.addActionListener(dashListener);
        
        statusLabel = new JLabel();
        panel = new DrawPanel(statusLabel);
        
        setLayout(new BorderLayout());
        
        JPanel topOptions = new JPanel();
        topOptions.setLayout(new BorderLayout());
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(undo);
        top.add(clear);
        top.add(colors);
        top.add(shapes);
        top.add(filled);
        topOptions.add(top, BorderLayout.NORTH);
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(gradient);
        bottom.add(firstColor);
        bottom.add(secondColor);
        bottom.add(strokeWidthLabel);
        bottom.add(strokeWidth);
        bottom.add(strokeDashLengthLabel);
        bottom.add(strokeDashLength);
        bottom.add(dashed);
        topOptions.add(bottom, BorderLayout.SOUTH);
        
        add(topOptions, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);        
        add(statusLabel, BorderLayout.SOUTH);
    }
    
    private class UndoHandler implements ActionListener {

        /**
         * When clicked, it will call the panel to clear the last shape
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.clearLastShape();
        }
        
    }
    
    private class ClearHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.clearDrawing();
        }
        
    }
    
    private class ColorHandler implements ItemListener {

        /**
         * Gets the color selected from the default options
         * and sets the panel default colors to be that
         * @param e ItemEvent
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                switch (colors.getSelectedIndex()) {
                    case 0:
                        panel.setCurrentColor(Color.BLACK);
                        break;
                    case 1:
                        panel.setCurrentColor(Color.RED);
                        break;
                    case 2:
                        panel.setCurrentColor(Color.GREEN);
                        break;
                    case 3:
                        panel.setCurrentColor(Color.BLUE);
                        break;
                    case 4:
                        panel.setCurrentColor(Color.ORANGE);
                        break;
                    case 5:
                        panel.setCurrentColor(Color.YELLOW);
                        break;
                }
            }
        }
        
    }
    
    private class ShapeHandler implements ItemListener {

        /**
         * If an option is selected, it gets the option and
         * sets it to be the shape selected
         * @param e ItemEvent
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                panel.setShapeType(shapes.getSelectedIndex());
            }
        }
        
    }
    
    private class FilledHandler implements ActionListener {

        /**
         * When clicked, it will set it so the panel current 
         * shape is filled
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setFilledShape(filled.isSelected());
        }
        
    }
    
    private class GradientHandler implements ActionListener {

        /**
         * It reverses the gradient option selected
         * If selected, it will create a gradient with the two colors selected
         * If not, it will just set the color to be the first color selected
         * @param e 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            isGradient = !isGradient;
            if (isGradient) {
                panel.setCurrentColor(new GradientPaint(0, 0, color1, 50, 50, color2, true));
            } else {
                panel.setCurrentColor(color1);
            }
        }
        
    }
    
    private class FirstColorHandler implements ActionListener {
        
        /**
         * Displays a JColorChooser which allows the user to selected
         * from a bunch of colors.
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JColorChooser colorChooser = new JColorChooser();
            JColorChooser.createDialog(null, "Choose Color", true, colorChooser, new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    color1 = colorChooser.getColor();
                }
            }, null).setVisible(true);
            isGradient = !isGradient;
            gradient.doClick();
        }
        
    }
    
    private class SecondColorHandler implements ActionListener {

        /**
         * Displays another JColorChooser in which sets the second color
         * to be the color selected
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JColorChooser colorChooser = new JColorChooser();
            JColorChooser.createDialog(null, "Choose Color", true, colorChooser, new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    color2 = colorChooser.getColor();
                }
            }, null).setVisible(true);
            isGradient = !isGradient;
            gradient.doClick();
        }
        
    }
    
    private class DashedHandler implements ActionListener {

        /**
         * When selected, it reverses the dashed setting.
         * It checks to make sure that the user has inputted a correct value
         * (greater than 0) and creates a stroke such that it creates gaps as dashes
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            isDashed = !isDashed;
            if (isDashed) {
                if (dashWidth <= 0) {
                    dashWidth = 1;
                    strokeDashLength.setText("1");
                }
                float[] dashes = {dashWidth};
                panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND, 10, dashes, 0));                
            } else {
                panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));            
            }
        }
        
    }
}
