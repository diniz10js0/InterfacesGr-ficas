import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercicio1 extends JFrame implements ActionListener {
    private JTextField txtTexto;
    private JButton btnMostrar, btnLimpar, btnSair;

    public Exercicio1() {
        super("Exercício 1");

        // Criando o campo de texto e os botões
        txtTexto = new JTextField(20);
        btnMostrar = new JButton("Mostrar");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        // Configurando o painel
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());
        painel.add(new JLabel("Texto:"));
        painel.add(txtTexto);
        painel.add(btnMostrar);
        painel.add(btnLimpar);
        painel.add(btnSair);

        // Adicionando o painel à janela
        add(painel);

        // Configurações da janela
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Adicionando ação aos botões
        btnMostrar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnSair.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMostrar) {
            // Mostrar conteúdo do campo de texto em JOptionPane
            JOptionPane.showMessageDialog(this, txtTexto.getText(), "Conteúdo do Texto", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == btnLimpar) {
            // Limpar o campo de texto
            txtTexto.setText("");
        } else if (e.getSource() == btnSair) {
            // Sair do programa
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Exercicio1();
    }
}
