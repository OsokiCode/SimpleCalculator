import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Serif", Font.BOLD,30);

    double num1 =0, num2 = 0, result = 0;
    char operator;

    Calculator(){

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50,25,300,50);
        textField.setFont(myFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);

        }

        for (int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50,430,100,50);
        delButton.setBounds(150,430,100,50);
        clrButton.setBounds(250,430,100,50);

        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);


        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
        bindKeys();
    }

    public static void main (String[] args){
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i< 10; i++){
            if (e.getSource()== numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
        }

        if (e.getSource()== addButton){
            if (textField.getText().isEmpty()) return;
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource()== subButton){
            if (textField.getText().isEmpty()) return;
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource()== mulButton){
            if (textField.getText().isEmpty()) return;
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (e.getSource()== divButton){
            if (textField.getText().isEmpty()) return;
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource()== equButton){
            num2 = Double.parseDouble(textField.getText());

            switch (operator){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        textField.setText("Error");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource()== clrButton){
            textField.setText("");
        }

        if (e.getSource() == delButton) {
            String text = textField.getText();
            if (!text.isEmpty()) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        }

        if (e.getSource()== negButton){
            double temp = Double.parseDouble(textField.getText());
            temp *=-1;
            textField.setText(String.valueOf(temp));
        }

    }

    private void bindKeys() {
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();

        for (int i = 0; i <= 9; i++) {
            int finalI = i;

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0 + i, 0), "num" + i);
            inputMap.put(KeyStroke.getKeyStroke("NUMPAD" + i), "num" + i);

            actionMap.put("num" + i, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    numberButtons[finalI].doClick();
                }
            });
        }

        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.SHIFT_DOWN_MASK), addButton, "add");
        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), subButton, "sub");
        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK), mulButton, "mul");
        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), divButton, "div");

        bindKeyStroke(KeyStroke.getKeyStroke("ADD"), addButton, "numpad_add");
        bindKeyStroke(KeyStroke.getKeyStroke("SUBTRACT"), subButton, "numpad_sub");
        bindKeyStroke(KeyStroke.getKeyStroke("MULTIPLY"), mulButton, "numpad_mul");
        bindKeyStroke(KeyStroke.getKeyStroke("DIVIDE"), divButton, "numpad_div");

        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), decButton, "dot");
        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0), decButton, "comma");
        bindKeyStroke(KeyStroke.getKeyStroke("DECIMAL"), decButton, "numpad_dot");

        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), equButton, "equals");

        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), delButton, "delete");

        bindKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), clrButton, "clear");
    }

    private void bindKeyStroke(KeyStroke keyStroke, JButton button, String name) {
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();

        inputMap.put(keyStroke, name);
        actionMap.put(name, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.doClick();
            }
        });
    }


}
