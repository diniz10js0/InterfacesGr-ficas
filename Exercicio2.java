import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Implementação básica das classes Relógio e Mostrador
class Mostrador {
    private int horas;
    private int minutos;

    public Mostrador() {
        this.horas = 0;
        this.minutos = 0;
    }

    public void setHora(int horas) {
        this.horas = Math.max(0, Math.min(23, horas));
    }

    public void setMinuto(int minutos) {
        this.minutos = Math.max(0, Math.min(59, minutos));
    }

    public void incrementaMinuto() {
        this.minutos++;
        if (this.minutos == 60) {
            this.minutos = 0;
            this.horas = (this.horas + 1) % 24;
        }
    }

    public String mostra() {
        return String.format("%02d:%02d", horas, minutos);
    }
}

class Relogio {
    private Mostrador mostrador;

    public Relogio() {
        mostrador = new Mostrador();
    }

    public void setHora(int horas) {
        mostrador.setHora(horas);
    }

    public void setMinuto(int minutos) {
        mostrador.setMinuto(minutos);
    }

    public void ticTac() {
        mostrador.incrementaMinuto();
    }

    public String mostra() {
        return mostrador.mostra();
    }
}

// Classe principal do exercício
public class Exercicio2 extends JFrame implements ActionListener {
    private JLabel lblRelogio;
    private JButton btnTicTac, btnHora, btnMinuto;
    private Relogio relogio;

    public Exercicio2() {
        super("Relógio");

        relogio = new Relogio();
        lblRelogio = new JLabel(relogio.mostra(), JLabel.CENTER);

        btnTicTac = new JButton("TicTac");
        btnHora = new JButton("Hora");
        btnMinuto = new JButton("Minuto");

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnTicTac);
        painelBotoes.add(btnHora);
        painelBotoes.add(btnMinuto);

        add(lblRelogio, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnTicTac.addActionListener(this);
        btnHora.addActionListener(this);
        btnMinuto.addActionListener(this);

        setSize(200, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTicTac) {
            relogio.ticTac();
        } else if (e.getSource() == btnHora) {
            String inputHora = JOptionPane.showInputDialog(this, "Ajuste a Hora (0-23):");
            try {
                int hora = Integer.parseInt(inputHora);
                relogio.setHora(hora);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido!");
            }
        } else if (e.getSource() == btnMinuto) {
            String inputMinuto = JOptionPane.showInputDialog(this, "Ajuste o Minuto (0-59):");
            try {
                int minuto = Integer.parseInt(inputMinuto);
                relogio.setMinuto(minuto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido!");
            }
        }
        lblRelogio.setText(relogio.mostra());
    }

    public static void main(String[] args) {
        new Exercicio2();
    }
}
