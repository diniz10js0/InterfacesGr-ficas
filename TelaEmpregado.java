import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Implementação das classes de Empregado (versão simplificada)
abstract class Empregado {
    protected String nome;
    protected int idade;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public abstract double calcularSalario();

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade;
    }
}

class Mensalista extends Empregado {
    private double salarioMensal;

    public void setSalarioMensal(double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    @Override
    public double calcularSalario() {
        return salarioMensal;
    }
}

class Comissionado extends Empregado {
    private double comissao;

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    @Override
    public double calcularSalario() {
        return comissao;
    }
}

class Horista extends Empregado {
    private double horasTrabalhadas;
    private double valorHora;

    public void setHorasTrabalhadas(double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public double calcularSalario() {
        return horasTrabalhadas * valorHora;
    }
}

class Tarefeiro extends Empregado {
    private double valorTarefa;

    public void setValorTarefa(double valorTarefa) {
        this.valorTarefa = valorTarefa;
    }

    @Override
    public double calcularSalario() {
        return valorTarefa;
    }
}

// Classe principal da interface gráfica
public class TelaEmpregado extends JFrame implements ActionListener {
    private JTextField txtNome, txtIdade, txtSalarioMensal, txtComissao, txtHoras, txtValorHora, txtValorTarefa, txtSalario;
    private JButton btnCalcular, btnLimpar, btnSair;

    public TelaEmpregado() {
        super("Cadastro de Empregado");

        // Campos de entrada
        txtNome = new JTextField(10);
        txtIdade = new JTextField(10);
        txtSalarioMensal = new JTextField(10);
        txtComissao = new JTextField(10);
        txtHoras = new JTextField(10);
        txtValorHora = new JTextField(10);
        txtValorTarefa = new JTextField(10);
        txtSalario = new JTextField(10);
        txtSalario.setEditable(false);

        btnCalcular = new JButton("Calcular");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        // Painel de entrada
        JPanel painelEntrada = new JPanel(new GridLayout(8, 2));
        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(txtNome);
        painelEntrada.add(new JLabel("Idade:"));
        painelEntrada.add(txtIdade);
        painelEntrada.add(new JLabel("Salário Mensal:"));
        painelEntrada.add(txtSalarioMensal);
        painelEntrada.add(new JLabel("Comissão:"));
        painelEntrada.add(txtComissao);
        painelEntrada.add(new JLabel("Horas Trabalhadas:"));
        painelEntrada.add(txtHoras);
        painelEntrada.add(new JLabel("Valor Hora:"));
        painelEntrada.add(txtValorHora);
        painelEntrada.add(new JLabel("Valor Tarefa:"));
        painelEntrada.add(txtValorTarefa);

        // Painel de saída
        JPanel painelSaida = new JPanel(new FlowLayout());
        painelSaida.add(new JLabel("Salário Calculado:"));
        painelSaida.add(txtSalario);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnCalcular);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSair);

        // Configurando a janela
        Container caixa = getContentPane();
        caixa.setLayout(new BorderLayout());
        caixa.add(painelEntrada, BorderLayout.NORTH);
        caixa.add(painelSaida, BorderLayout.CENTER);
        caixa.add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        btnCalcular.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnSair.addActionListener(this);

        // Configurações da janela
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            Empregado empregado = null;

            // Determina o tipo de empregado com base nos campos preenchidos
            if (!txtSalarioMensal.getText().isEmpty()) {
                Mensalista mensalista = new Mensalista();
                mensalista.setSalarioMensal(Double.parseDouble(txtSalarioMensal.getText()));
                empregado = mensalista;
            } else if (!txtComissao.getText().isEmpty()) {
                Comissionado comissionado = new Comissionado();
                comissionado.setComissao(Double.parseDouble(txtComissao.getText()));
                empregado = comissionado;
            } else if (!txtHoras.getText().isEmpty() && !txtValorHora.getText().isEmpty()) {
                Horista horista = new Horista();
                horista.setHorasTrabalhadas(Double.parseDouble(txtHoras.getText()));
                horista.setValorHora(Double.parseDouble(txtValorHora.getText()));
                empregado = horista;
            } else if (!txtValorTarefa.getText().isEmpty()) {
                Tarefeiro tarefeiro = new Tarefeiro();
                tarefeiro.setValorTarefa(Double.parseDouble(txtValorTarefa.getText()));
                empregado = tarefeiro;
            }

            if (empregado != null) {
                empregado.setNome(txtNome.getText());
                empregado.setIdade(Integer.parseInt(txtIdade.getText()));
                txtSalario.setText(String.format("%.2f", empregado.calcularSalario()));
                JOptionPane.showMessageDialog(this, empregado.toString() + "\nSalário: " + txtSalario.getText(), "Informações do Empregado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Dados insuficientes para cálculo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnLimpar) {
            txtNome.setText("");
            txtIdade.setText("");
            txtSalarioMensal.setText("");
            txtComissao.setText("");
            txtHoras.setText("");
            txtValorHora.setText("");
            txtValorTarefa.setText("");
            txtSalario.setText("");
        } else if (e.getSource() == btnSair) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new TelaEmpregado();
    }
}
