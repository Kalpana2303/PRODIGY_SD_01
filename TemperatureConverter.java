///*
// * Temperature Converter - GUI Application
// * Converts Celsius, Fahrenheit, and Kelvin
// * Single file, OOP-friendly structure
// */
///*
// * Temperature Converter GUI - Converts Celsius, Fahrenheit, and Kelvin
// */
//package prodigy_1;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class TemperatureConverter {
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            createAndShowGUI();
//        });
//    }
//
//    private static void createAndShowGUI() {
//        JFrame frame = new JFrame("Temperature Converter");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//        frame.setLayout(new FlowLayout());
//
//        JLabel inputLabel = new JLabel("Enter Temperature:");
//        JTextField inputField = new JTextField(10);
//
//        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
//        JComboBox<String> unitSelector = new JComboBox<>(units);
//
//        JButton convertButton = new JButton("Convert");
//
//        JTextArea resultArea = new JTextArea(6, 30);
//        resultArea.setEditable(false);
//        resultArea.setLineWrap(true);
//        resultArea.setWrapStyleWord(true);
//        JScrollPane scrollPane = new JScrollPane(resultArea);
//
//        convertButton.addActionListener(e -> {
//            String inputText = inputField.getText().trim();
//            String selectedUnit = (String) unitSelector.getSelectedItem();
//
//            try {
//                double inputTemp = Double.parseDouble(inputText);
//                String result = convertTemperature(inputTemp, selectedUnit);
//                resultArea.setText(result);
//            } catch (NumberFormatException ex) {
//                resultArea.setText("Invalid input. Please enter a numeric temperature.");
//            }
//        });
//
//        frame.add(inputLabel);
//        frame.add(inputField);
//        frame.add(unitSelector);
//        frame.add(convertButton);
//        frame.add(scrollPane);
//
//        frame.setVisible(true);
//    }
//
//    private static String convertTemperature(double temp, String unit) {
//        double celsius = 0, fahrenheit = 0, kelvin = 0;
//        StringBuilder result = new StringBuilder();
//
//        switch (unit) {
//            case "Celsius":
//                celsius = temp;
//                fahrenheit = (temp * 9/5) + 32;
//                kelvin = temp + 273.15;
//                result.append(String.format("Input: %.2f °C\n", celsius));
//                result.append(String.format("Fahrenheit: %.2f °F\n", fahrenheit));
//                result.append(String.format("Kelvin: %.2f K", kelvin));
//                break;
//            case "Fahrenheit":
//                fahrenheit = temp;
//                celsius = (temp - 32) * 5/9;
//                kelvin = celsius + 273.15;
//                result.append(String.format("Input: %.2f °F\n", fahrenheit));
//                result.append(String.format("Celsius: %.2f °C\n", celsius));
//                result.append(String.format("Kelvin: %.2f K", kelvin));
//                break;
//            case "Kelvin":
//                kelvin = temp;
//                celsius = temp - 273.15;
//                fahrenheit = (celsius * 9/5) + 32;
//                result.append(String.format("Input: %.2f K\n", kelvin));
//                result.append(String.format("Celsius: %.2f °C\n", celsius));
//                result.append(String.format("Fahrenheit: %.2f °F", fahrenheit));
//                break;
//            default:
//                result.append("Unknown unit selected.");
//        }
//
//        return result.toString();
//    }
//}


package prodigy_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverter().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Temperature Converter - GridBagLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 180);
        frame.setLocationRelativeTo(null);

        Container pane = frame.getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel inputLabel = new JLabel("Temperature:");
        JTextField inputField = new JTextField(10);

        JLabel unitLabel = new JLabel("Unit:");
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        JComboBox<String> unitSelector = new JComboBox<>(units);

        JLabel outputLabel = new JLabel("Converted values will appear here.");
        outputLabel.setForeground(new Color(0, 70, 140));
        outputLabel.setPreferredSize(new Dimension(400, 40));

        // Row 0, col 0 - input label
        c.gridx = 0; c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(10, 10, 5, 5);
        pane.add(inputLabel, c);

        // Row 0, col 1 - input field
        c.gridx = 1; c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 5, 10);
        pane.add(inputField, c);

        // Row 1, col 0 - unit label
        c.gridx = 0; c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 5, 5);
        pane.add(unitLabel, c);

        // Row 1, col 1 - unit combo
        c.gridx = 1; c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 0, 5, 10);
        pane.add(unitSelector, c);

        // Row 2, col 0 and 1 - output label (span 2 columns)
        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(15, 10, 10, 10);
        pane.add(outputLabel, c);

        // Only convert on Enter pressed in inputField
        inputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performConversion(inputField, unitSelector, outputLabel);
                }
            }
        });

        // You can still change unit anytime, but no automatic conversion on combo change
        // (Only Enter triggers conversion now)

        frame.setVisible(true);
    }

    private void performConversion(JTextField inputField, JComboBox<String> unitSelector, JLabel outputLabel) {
        String inputText = inputField.getText().trim();
        String unit = (String) unitSelector.getSelectedItem();

        try {
            double temp = Double.parseDouble(inputText);
            String result = TemperatureLogic.convert(temp, unit);
            // convert multiline text to html for JLabel multiline
            outputLabel.setText("<html>" + result.replaceAll("\n", "<br>") + "</html>");
        } catch (NumberFormatException ex) {
            outputLabel.setText("Invalid input! Enter a numeric temperature and press Enter.");
        }
    }
}

class TemperatureLogic {

    public static String convert(double temp, String unit) {
        double celsius = 0, fahrenheit = 0, kelvin = 0;
        StringBuilder sb = new StringBuilder();

        switch (unit) {
            case "Celsius":
                celsius = temp;
                fahrenheit = (temp * 9 / 5) + 32;
                kelvin = temp + 273.15;
                sb.append(String.format("Input: %.2f °C\n", celsius));
                sb.append(String.format("Fahrenheit: %.2f °F\n", fahrenheit));
                sb.append(String.format("Kelvin: %.2f K", kelvin));
                break;

            case "Fahrenheit":
                fahrenheit = temp;
                celsius = (temp - 32) * 5 / 9;
                kelvin = celsius + 273.15;
                sb.append(String.format("Input: %.2f °F\n", fahrenheit));
                sb.append(String.format("Celsius: %.2f °C\n", celsius));
                sb.append(String.format("Kelvin: %.2f K", kelvin));
                break;

            case "Kelvin":
                kelvin = temp;
                celsius = temp - 273.15;
                fahrenheit = (celsius * 9 / 5) + 32;
                sb.append(String.format("Input: %.2f K\n", kelvin));
                sb.append(String.format("Celsius: %.2f °C\n", celsius));
                sb.append(String.format("Fahrenheit: %.2f °F", fahrenheit));
                break;

            default:
                sb.append("Unknown unit selected.");
        }
        return sb.toString();
    }
}
