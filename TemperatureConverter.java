/*
 * Temperature Converter - GUI Application
 * Converts Celsius, Fahrenheit, and Kelvin
 * Single file, OOP-friendly structure
 */

package prodigy_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JLabel inputLabel = new JLabel("Enter Temperature:");
        JTextField inputField = new JTextField(10);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        JComboBox<String> unitSelector = new JComboBox<>(units);

        JButton convertButton = new JButton("Convert");

        JTextArea resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        convertButton.addActionListener(e -> {
            String inputText = inputField.getText().trim();
            String selectedUnit = (String) unitSelector.getSelectedItem();

            try {
                double inputTemp = Double.parseDouble(inputText);
                String result = convertTemperature(inputTemp, selectedUnit);
                resultArea.setText(result);
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter a numeric temperature.");
            }
        });

        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(unitSelector);
        frame.add(convertButton);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    private static String convertTemperature(double temp, String unit) {
        double celsius = 0, fahrenheit = 0, kelvin = 0;
        StringBuilder result = new StringBuilder();

        switch (unit) {
            case "Celsius":
                celsius = temp;
                fahrenheit = (temp * 9/5) + 32;
                kelvin = temp + 273.15;
                result.append(String.format("Input: %.2f °C\n", celsius));
                result.append(String.format("Fahrenheit: %.2f °F\n", fahrenheit));
                result.append(String.format("Kelvin: %.2f K", kelvin));
                break;
            case "Fahrenheit":
                fahrenheit = temp;
                celsius = (temp - 32) * 5/9;
                kelvin = celsius + 273.15;
                result.append(String.format("Input: %.2f °F\n", fahrenheit));
                result.append(String.format("Celsius: %.2f °C\n", celsius));
                result.append(String.format("Kelvin: %.2f K", kelvin));
                break;
            case "Kelvin":
                kelvin = temp;
                celsius = temp - 273.15;
                fahrenheit = (celsius * 9/5) + 32;
                result.append(String.format("Input: %.2f K\n", kelvin));
                result.append(String.format("Celsius: %.2f °C\n", celsius));
                result.append(String.format("Fahrenheit: %.2f °F", fahrenheit));
                break;
            default:
                result.append("Unknown unit selected.");
        }

        return result.toString();
    }
}


