package study;

import java.awt.*;
import javax.swing.*;

public class Calculator extends JFrame {

    JTextField display;
    JPanel panel;
    JButton button;
    double operand1, operand2, answer;
    String operator;

    Calculator() {
        this.setBounds(100, 100, 300, 500);
        this.setTitle("계산기");
        this.setLayout(new BorderLayout());

        display = new JTextField(30);
        display.setText("0");
        display.setFont(new Font("궁서체", Font.BOLD, 35));
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        this.add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4));

        String[] buttonLabels = {
                "%", "CE", "C", "<-",
                "1/x", "x^2", "Sqrt", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        for (String label : buttonLabels) {
            button = new JButton(label);
            button.addActionListener(e -> handleButtonClick(label));
            panel.add(button);
        }

        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "C":
                display.setText("0");
                break;
            case "<-":
                String currentText = display.getText();
                if (currentText.length() > 1) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                } else {
                    display.setText("0");
                }
                break;
            case "=":
                operand2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        answer = operand1 + operand2;
                        break;
                    case "-":
                        answer = operand1 - operand2;
                        break;
                    case "*":
                        answer = operand1 * operand2;
                        break;
                    case "/":
                        answer = operand1 / operand2;
                        break;
                }
                display.setText(String.valueOf(answer));
                break;
            default:
                if (Character.isDigit(label.charAt(0))) {
                    if (display.getText().equals("0")) {
                        display.setText(label);
                    } else {
                        display.setText(display.getText() + label);
                    }
                } else {
                    operand1 = Double.parseDouble(display.getText());
                    operator = label;
                    display.setText("0");
                }
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }

}
