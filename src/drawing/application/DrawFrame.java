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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Displays the panel which allows the user to choose options from button and
 * the panel to draw on
 *
 * @author Lawrence and Nate
 */
public class DrawFrame extends JFrame {

    private final JButton undo;
    private final JButton redo;
    private final JButton clear;
    private final JComboBox<String> colors;
    private final JComboBox<String> shapes;
    private final JCheckBox filled;
    private final JButton selectFile;
    private final JButton detectFace;

    private final JCheckBox gradient;
    private final JButton firstColor;
    private final JButton swapColors;
    private final JButton secondColor;
    private final JLabel strokeWidthLabel;
    private final JTextField strokeWidth;
    private final JLabel strokeDashLengthLabel;
    private final JTextField strokeDashLength;
    private final JCheckBox dashed;

    private final DrawPanel panel;
    private final JLabel statusLabel;

    private boolean isGradient;
    private Color currentColor;
    private Color gradientColor1;
    private Color gradientColor2;
    private int lineWidth;
    private int dashWidth;
    private boolean isDashed;
    private ImageIcon imageIcon;
    private BufferedImage image;

    private String absoluteFilePath;

    private final String[] colorOptions = {"Black", "Blue", "Cyan", "Dark Gray",
        "Gray", "Green", "Light Gray", "Magenta", "Orange", "Pink", "Red",
        "White", "Yellow"};
    private final String[] shapeOptions = {"Free Form", "Line", "Oval",
        "Rectangle", "Arc"};

    public DrawFrame() {
        isGradient = false;
        currentColor = Color.BLACK;
        gradientColor1 = Color.BLACK;
        gradientColor2 = Color.BLACK;
        lineWidth = 1;
        dashWidth = 0;
        isDashed = false;
        absoluteFilePath = "";

        undo = new JButton("Undo");
        undo.addActionListener(new UndoHandler());
        redo = new JButton("Redo");
        redo.addActionListener(new RedoHandler());
        clear = new JButton("Clear");
        clear.addActionListener(new ClearHandler());
        colors = new JComboBox(colorOptions);
        colors.addItemListener(new ColorHandler());
        shapes = new JComboBox(shapeOptions);
        shapes.addItemListener(new ShapeHandler());
        filled = new JCheckBox("Filled");
        filled.addActionListener(new FilledHandler());
        selectFile = new JButton("Choose File:");
        selectFile.addActionListener(new FileChooserHandler());
        detectFace = new JButton("Detect Face");
        detectFace.addActionListener(new FaceDetectionHandler());

        gradient = new JCheckBox("Use Gradient");
        gradient.addActionListener(new GradientHandler());
        firstColor = new JButton();
        firstColor.setBackground(gradientColor1);
        firstColor.addActionListener(new FirstColorHandler());
        swapColors = new JButton("<>");
        swapColors.addActionListener(new SwapColorHandler());
        secondColor = new JButton();
        secondColor.setBackground(gradientColor2);
        secondColor.addActionListener(new SecondColorHandler());
        strokeWidthLabel = new JLabel("Line Width:");
        strokeWidth = new JTextField("", 3);
        //Create a new document listener because an action listener
        //would require the user to press enter to register
        strokeWidth.getDocument().addDocumentListener(new StrokeWidthHandler());
        strokeDashLengthLabel = new JLabel("Dash Length:");
        strokeDashLength = new JTextField("", 3);
        //Create a new document listener because an action listener
        //would require the user to press enter to register
        strokeDashLength.getDocument().addDocumentListener(new StrokeDashLengthHandler());
        dashed = new JCheckBox("Dashed");
        dashed.addActionListener(new DashedHandler());

        statusLabel = new JLabel();
        panel = new DrawPanel(statusLabel);

        setLayout(new BorderLayout());

        JPanel topOptions = new JPanel();
        topOptions.setLayout(new BorderLayout());
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(undo);
        top.add(redo);
        top.add(clear);
        top.add(colors);
        top.add(shapes);
        top.add(filled);
        top.add(selectFile);
        top.add(detectFace);
        topOptions.add(top, BorderLayout.NORTH);
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(gradient);
        bottom.add(firstColor);
        bottom.add(swapColors);
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
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.clearLastShape();
        }

    }

    private class RedoHandler implements ActionListener {

        /**
         * When clicked, it will redo the last shaped removed
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.redoLastShape();
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
         * Gets the color selected from the default options and sets the panel
         * default colors to be that
         *
         * @param e ItemEvent
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                switch (colors.getSelectedIndex()) {
                    case 0:
                        currentColor = Color.BLACK;
                        panel.setCurrentColor(Color.BLACK);
                        break;
                    case 1:
                        currentColor = Color.BLUE;
                        panel.setCurrentColor(Color.BLUE);
                        break;
                    case 2:
                        currentColor = Color.CYAN;
                        panel.setCurrentColor(Color.CYAN);
                        break;
                    case 3:
                        currentColor = Color.DARK_GRAY;
                        panel.setCurrentColor(Color.DARK_GRAY);
                        break;
                    case 4:
                        currentColor = Color.GRAY;
                        panel.setCurrentColor(Color.GRAY);
                        break;
                    case 5:
                        currentColor = Color.GREEN;
                        panel.setCurrentColor(Color.GREEN);
                        break;
                    case 6:
                        currentColor = Color.LIGHT_GRAY;
                        panel.setCurrentColor(Color.LIGHT_GRAY);
                        break;
                    case 7:
                        currentColor = Color.MAGENTA;
                        panel.setCurrentColor(Color.MAGENTA);
                        break;
                    case 8:
                        currentColor = Color.ORANGE;
                        panel.setCurrentColor(Color.ORANGE);
                        break;
                    case 9:
                        currentColor = Color.PINK;
                        panel.setCurrentColor(Color.PINK);
                        break;
                    case 10:
                        currentColor = Color.RED;
                        panel.setCurrentColor(Color.RED);
                        break;
                    case 11:
                        currentColor = Color.WHITE;
                        panel.setCurrentColor(Color.WHITE);
                        break;
                    default:    //Last option is yellow
                        currentColor = Color.YELLOW;
                        panel.setCurrentColor(Color.YELLOW);
                        break;
                }
            }
        }

    }

    private class ShapeHandler implements ItemListener {

        /**
         * If an option is selected, it gets the option and sets it to be the
         * shape selected
         *
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
         * When clicked, it will set it so the panel current shape is filled
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setFilledShape(filled.isSelected());
        }

    }

    private class FileChooserHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showDialog(panel, null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    absoluteFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    image = ImageIO.read(file);
                    panel.setImage(image);
                } catch (IOException ex) {
                    Logger.getLogger(DrawFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }

    }

    private class FaceDetectionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            (new FaceDetection(absoluteFilePath)).run();
        }

    }

    private class GradientHandler implements ActionListener {

        /**
         * It reverses the gradient option selected If selected, it will create
         * a gradient with the two colors selected If not, it will just set the
         * color to be the first color selected
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            isGradient = !isGradient;
            if (isGradient) {
                panel.setCurrentColor(new GradientPaint(0, 0, gradientColor1, 50, 50, gradientColor2, true));
            } else {
                panel.setCurrentColor(gradientColor1);
            }
        }

    }

    private class FirstColorHandler implements ActionListener {

        /**
         * Displays a JColorChooser which allows the user to selected from a
         * bunch of colors.
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JColorChooser colorChooser = new JColorChooser();
            JColorChooser.createDialog(null, "Choose Color", true, colorChooser, (ActionEvent e1) -> {
                gradientColor1 = colorChooser.getColor();
                firstColor.setBackground(gradientColor1);
                if (isGradient) {   //Checks to make sure new color updates gradient
                    panel.setCurrentColor(new GradientPaint(0, 0, gradientColor1,
                            50, 50, gradientColor2, true));
                } else {
                    panel.setCurrentColor(currentColor);
                }
            }, null).setVisible(true);

        }

    }

    private class SwapColorHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Color temp = gradientColor1;
            gradientColor1 = gradientColor2;
            gradientColor2 = temp;
            firstColor.setBackground(gradientColor1);
            secondColor.setBackground(gradientColor2);
        }

    }

    private class SecondColorHandler implements ActionListener {

        /**
         * Displays another JColorChooser in which sets the second color to be
         * the color selected
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JColorChooser colorChooser = new JColorChooser();
            JColorChooser.createDialog(null, "Choose Color", true, colorChooser, (ActionEvent e1) -> {
                gradientColor2 = colorChooser.getColor();
                secondColor.setBackground(gradientColor2);
                if (isGradient) {   //Checks so new color changes the gradient
                    panel.setCurrentColor(new GradientPaint(0, 0, gradientColor1,
                            50, 50, gradientColor2, true));
                } else {
                    panel.setCurrentColor(currentColor);
                }
            }, null).setVisible(true);
        }

    }

    private class StrokeWidthHandler implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                lineWidth = Integer.parseInt(strokeWidth.getText());
            } catch (NumberFormatException ex) {
                lineWidth = 1;
            }
            if (lineWidth < 1) {
                lineWidth = 1;
            }
            if (isDashed) {
                float[] dashes = {dashWidth};
                panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10, dashes, 0));
            } else {
                panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                lineWidth = Integer.parseInt(strokeWidth.getText());
            } catch (NumberFormatException ex) {
                lineWidth = 1;
            }
            if (lineWidth < 1) {
                lineWidth = 1;
            }
            if (isDashed) {
                float[] dashes = {dashWidth};
                panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10, dashes, 0));
            } else {
                panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            try {
                lineWidth = Integer.parseInt(strokeWidth.getText());
            } catch (NumberFormatException ex) {
                lineWidth = 1;
            }
            if (lineWidth < 1) {
                lineWidth = 1;
            }
            if (isDashed) {
                float[] dashes = {dashWidth};
                panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10, dashes, 0));
            } else {
                panel.setCurrentStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
            }
        }
    }

    private class StrokeDashLengthHandler implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                dashWidth = Integer.parseInt(strokeDashLength.getText());
            } catch (NumberFormatException ex) {
                dashWidth = 1;
            }
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

        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                dashWidth = Integer.parseInt(strokeDashLength.getText());
            } catch (NumberFormatException ex) {
                dashWidth = 1;
            }
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

        @Override
        public void changedUpdate(DocumentEvent e) {
            try {
                dashWidth = Integer.parseInt(strokeDashLength.getText());
            } catch (NumberFormatException ex) {
                dashWidth = 1;
            }
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

    private class DashedHandler implements ActionListener {

        /**
         * When selected, it reverses the dashed setting. It checks to make sure
         * that the user has inputted a correct value (greater than 0) and
         * creates a stroke such that it creates gaps as dashes
         *
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
