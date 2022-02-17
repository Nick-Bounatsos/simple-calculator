import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Calculator implements ActionListener {
    // Widgets
    JFrame frame;
    JTextField screen;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[18];
    JButton addButton, subButton, multButton, divButton;
    JButton decimalButton, clearButton, equalsButton, backSpaceButton;
    JButton negateButton, piButton, eButton, floorDivButton, modButton;
    JButton sinButton, cosButton, tanButton, reverseButton;
    JButton powButton;
    JPanel panel;

    // Customization
    Font screenFont = new Font("Constantia", Font.PLAIN, 22);
    Font buttonFont = new Font("Constantia", Font.BOLD, 18);
    Color screenColour = new Color(218, 218, 218);
    Color numbersColour = new Color(210, 210, 210);
    Color clearButtonColour = new Color(200, 100, 100);
    Color equalsButtonColour = new Color(100, 100, 200);


    // Dimensions
    final short FRAME_WIDTH = 350;
    final short FRAME_HEIGHT = 450;
    final byte GAP_BETWEEN_BUTTONS = 6;
    final short SCREEN_WIDTH = 300;
    final short SCREEN_HEIGHT = 50;
    final short ALIGNMENT_X = (FRAME_WIDTH - SCREEN_WIDTH) / 2 - 10;
    final short SCREEN_Y = 15;
    final short PANEL_Y = SCREEN_Y + 65; // SCREEN_Y + the desired gap between those
    final byte PANEL_ROWS = 7;
    final byte PANEL_COLS = 4;
    final short PANEL_HEIGHT = FRAME_HEIGHT - SCREEN_HEIGHT - 100;


    // Variables
    String operator;
    double num1, num2, result;
    boolean decimalExists = false;


    // Constructor
    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setLayout(null);

        screen = new JTextField();
        screen.setBounds(ALIGNMENT_X, SCREEN_Y, SCREEN_WIDTH, SCREEN_HEIGHT);
        screen.setFont(screenFont);
        screen.setBackground(screenColour);
        screen.setEditable(false);

        for (byte number = 0; number < 10; number++) {
            numberButtons[number] = new JButton(String.valueOf(number));
            numberButtons[number].setFocusable(false);
            numberButtons[number].addActionListener(this);
            numberButtons[number].setFont(buttonFont);
            numberButtons[number].setBackground(numbersColour);
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        multButton = new JButton("*");
        divButton = new JButton("/");
        decimalButton = new JButton(".");
        clearButton = new JButton("C");
        backSpaceButton = new JButton("Er");
        equalsButton = new JButton("=");
        negateButton = new JButton("+/-");
        powButton = new JButton("^");
        floorDivButton = new JButton("//");
        modButton = new JButton("%");
        piButton = new JButton("π");
        eButton = new JButton("e");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        reverseButton = new JButton("1/x");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = multButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decimalButton;
        functionButtons[5] = clearButton;
        functionButtons[6] = backSpaceButton;
        functionButtons[7] = equalsButton;
        functionButtons[8] = negateButton;
        functionButtons[9] = powButton;
        functionButtons[10] = floorDivButton;
        functionButtons[11] = modButton;
        functionButtons[12] = piButton;
        functionButtons[13] = eButton;
        functionButtons[14] = sinButton;
        functionButtons[15] = cosButton;
        functionButtons[16] = tanButton;
        functionButtons[17] = reverseButton;

        final byte amountOfFunctions = (byte) (functionButtons.length - 1);
        for (byte i = 0; i <= amountOfFunctions; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(buttonFont);
            functionButtons[i].setFocusable(false);
            if (functionButtons[i] == clearButton)
                functionButtons[i].setBackground(clearButtonColour);
            else if (functionButtons[i] == equalsButton)
                functionButtons[i].setBackground(equalsButtonColour);
        }

        // Panel for the buttons
        panel = new JPanel();
        panel.setBounds(ALIGNMENT_X, PANEL_Y, SCREEN_WIDTH, PANEL_HEIGHT);
        panel.setLayout(new GridLayout(PANEL_ROWS, PANEL_COLS, GAP_BETWEEN_BUTTONS, GAP_BETWEEN_BUTTONS));

        // Adding the buttons row by row

        panel.add(sinButton);
        panel.add(cosButton);
        panel.add(tanButton);
        panel.add(reverseButton);

        panel.add(floorDivButton);
        panel.add(modButton);
        panel.add(powButton);
        panel.add(clearButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(multButton);

        panel.add(negateButton);
        panel.add(numberButtons[0]);
        panel.add(decimalButton);
        panel.add(divButton);

        panel.add(piButton);
        panel.add(eButton);
        panel.add(backSpaceButton);
        panel.add(equalsButton);

        frame.add(panel);
        frame.add(screen);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator app = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (byte number = 0; number < 10; number++) {
            if (e.getSource() == numberButtons[number]) {
                screen.setText(screen.getText().concat(String.valueOf(number)));
            }
        }
        if (e.getSource() == decimalButton && !decimalExists) {
            screen.setText(screen.getText().concat("."));
            decimalExists = true;
        }
        if (e.getSource() == addButton) {
            try {
                num1 = Double.parseDouble(screen.getText());
                operator = "+";
                screen.setText("");
                decimalExists = false;
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == subButton) {
            try {
                num1 = Double.parseDouble(screen.getText());
                operator = "-";
                screen.setText("");
                decimalExists = false;
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == multButton) {
            try {
                num1 = Double.parseDouble(screen.getText());
                operator = "*";
                screen.setText("");
                decimalExists = false;
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == divButton) {
            try {
                num1 = Double.parseDouble(screen.getText());
                operator = "/";
                screen.setText("");
                decimalExists = false;
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == negateButton) {
            try {
                screen.setText(String.valueOf(-Double.parseDouble(screen.getText())));
                fixPointZeroIssue();
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == floorDivButton) {
            try {
                num1 = Double.parseDouble(screen.getText());
                operator = "//";
                screen.setText("");
                decimalExists = false;
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == modButton) {
            try {
                num1 = Double.parseDouble(screen.getText());
                operator = "%";
                screen.setText("");
                decimalExists = false;
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == powButton) {
            try {
                num1 = Double.parseDouble(screen.getText());
                screen.setText("");
                operator = "^";
                decimalExists = false;
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == sinButton) {
            try {
                final double degrees = Double.parseDouble(screen.getText());
                final double tempResult = Math.round(Math.sin(degrees * Math.PI / 180) * 1e8) / 1e8;
                screen.setText(String.valueOf(tempResult));
                fixPointZeroIssue();
                decimalExists = checkForDecimal();
                operator = null; // To avoid logging to memory
                logCalculation("Sin(" + degrees + ") = " + tempResult);
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == cosButton) {
            try {
                final double degrees = Double.parseDouble(screen.getText());
                final double tempResult = Math.round(Math.cos(degrees * Math.PI / 180) * 1e8) / 1e8;
                screen.setText(String.valueOf(tempResult));
                fixPointZeroIssue();
                decimalExists = checkForDecimal();
                operator = null; // To avoid logging to memory
                logCalculation("Cos(" + degrees + ") = " + tempResult);
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == tanButton) {
            try {
                final double degrees = Double.parseDouble(screen.getText());
                final double tempResult = Math.round(Math.tan(degrees * Math.PI / 180) * 1e8) / 1e8;
                screen.setText(String.valueOf(tempResult));
                fixPointZeroIssue();
                decimalExists = checkForDecimal();
                operator = null; // To avoid logging to memory
                logCalculation("Tan(" + degrees + ") = " + tempResult);
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == reverseButton) {
            try {
                screen.setText(String.valueOf(1 / Double.parseDouble(screen.getText())));
                fixPointZeroIssue();
                decimalExists = checkForDecimal();
            } catch (NumberFormatException ignored) {
            }
        }
        if (e.getSource() == clearButton) {
            screen.setText("");
            operator = null;
            decimalExists = false;
        }
        if (e.getSource() == backSpaceButton) {
            try {
                if (screen.getText().endsWith("."))
                    decimalExists = false;
                screen.setText(screen.getText().substring(0, screen.getText().length() - 1));
            } catch (StringIndexOutOfBoundsException ignored) {
            }
        }
        if (e.getSource() == piButton && !decimalExists) {
            screen.setText(screen.getText().concat("3.14159"));
            decimalExists = true;
        }
        if (e.getSource() == eButton && !decimalExists) {
            screen.setText("2.718");
            decimalExists = true;
        }
        if (e.getSource() == equalsButton) {
            try {
                num2 = Double.parseDouble(screen.getText());
                switch (operator) { // This form of switch, has a built in break statement
                    case "+" :
                    	result = num1 + num2;
                    	break;
                    case "-" :
                    	result = num1 - num2;
                    	break;
                    case "*" :
                    	result = num1 * num2;
                    	break;
                    case "/" :
                    	result = num1 / num2;
                    	break;
                    case "//" :
                    	result = (long) num1 / (long) num2;
                    	break;
                    case "%" :
                    	result = num1 % num2;
                    	break;
                    case "^" :
                    	result = Math.pow(num1, num2);
                    	break;
                }
            } catch (Exception ignored) {
            }
            result = Math.round(result * 1e12) / 1e12;
            if (operator != null)
                logCalculation(num1 + " " + operator + " " + num2 + " = " + result);

            if (String.valueOf(result).endsWith(".0")) {
                screen.setText(String.valueOf(result).replace(".0", ""));
                decimalExists = false;
            } else {
                screen.setText(String.valueOf(result));
                decimalExists = true;
            }
        }
    }

    private static void logCalculation(String calculation) {
        try {
            File memoryFile = new File("Files/CalculatorMemory.txt");
            FileWriter writer = new FileWriter(memoryFile, true);
            writer.write(calculation + System.lineSeparator());
            writer.close();
        } catch (IOException ignored) {
        }
    }

    // Checks if there's a ".0" on screen. If there is, removes it.
    private void fixPointZeroIssue() {
        if (screen.getText().endsWith(".0"))
            screen.setText(screen.getText().replace(".0", ""));
    }

    private boolean checkForDecimal() {
        final char[] outputCharArray = screen.getText().toCharArray();
        for (char character : outputCharArray) {
            if (character == '.')
                return true;
        }
        return false;
    }
}
