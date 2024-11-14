import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    private JTextField display;
    private int operador1 = 0, operador2 = 0;
    private String operador = "";
    private boolean novoOperador = true;

    public Calculadora() {
        super("Calculadora");

        // Configuração do display
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        // Configuração dos botões
        JPanel painelNumeros = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) {
            addButton(painelNumeros, String.valueOf(i));
        }
        addButton(painelNumeros, "0");

        JPanel painelOperadores = new JPanel(new GridLayout(4, 1));
        addButton(painelOperadores, "+");
        addButton(painelOperadores, "-");
        addButton(painelOperadores, "*");
        addButton(painelOperadores, "/");

        JButton btnIgual = new JButton("=");
        btnIgual.addActionListener(this);

        JButton btnLimpar = new JButton("C");
        btnLimpar.addActionListener(e -> {
            display.setText("");
            operador1 = 0;
            operador2 = 0;
            operador = "";
            novoOperador = true;
        });

        // Configurando o layout da janela
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(display, BorderLayout.NORTH);
        painelPrincipal.add(painelNumeros, BorderLayout.CENTER);
        
        JPanel painelLateral = new JPanel(new BorderLayout());
        painelLateral.add(painelOperadores, BorderLayout.CENTER);
        painelLateral.add(btnIgual, BorderLayout.SOUTH);
        
        painelPrincipal.add(painelLateral, BorderLayout.EAST);
        painelPrincipal.add(btnLimpar, BorderLayout.SOUTH);

        add(painelPrincipal);

        // Configuração da janela
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButton(JPanel panel, String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9]")) {  // Se for um número
            if (novoOperador) {
                display.setText(comando);
                novoOperador = false;
            } else {
                display.setText(display.getText() + comando);
            }
        } else if (comando.matches("[+\\-*/]")) {  // Se for um operador
            operador1 = Integer.parseInt(display.getText());
            operador = comando;
            novoOperador = true;
        } else if (comando.equals("=")) {  // Se for o igual
            operador2 = Integer.parseInt(display.getText());
            int resultado = calcular(operador1, operador2, operador);
            display.setText(String.valueOf(resultado));
            novoOperador = true;
        }
    }

    private int calcular(int operador1, int operador2, String operador) {
        return switch (operador) {
            case "+" -> operador1 + operador2;
            case "-" -> operador1 - operador2;
            case "*" -> operador1 * operador2;
            case "/" -> operador2 != 0 ? operador1 / operador2 : 0;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
