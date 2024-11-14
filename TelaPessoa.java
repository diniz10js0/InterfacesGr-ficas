import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Implementação das classes de Pessoa Física e Empregado
abstract class PessoaFisica {
    protected String nome;
    protected int idade;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade;
    }
}

class Desempregado extends PessoaFisica {
    @Override
    public String toString() {
        return super.toString() + " - Desempregado";
    }
}

abstract class Empregado extends PessoaFisica {
    public abstract double calcularSalario();
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

    @Override
    public String toString() {
        return super.toString() + " - Mensalista";
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

    @Override
    public String toString() {
        return super.toString() + " - Comissionado";
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

    @Override
    public String toString() {
        return super.toString() + " - Horista";
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

    @Override
    public String toString() {
        return super.toString() + " - Tarefeiro";
    }
}

// Classe principal da interface gráfica
public class TelaPessoa extends JFrame implements ActionListener {
    private JTextField txtNome, txtIdade, txtSalarioMensal, txtComissao, txtHoras, txtValorHora, txtValorTarefa, txtSalario;
    private JButton btnCalcular, btnLimpar, btnSair;
    private JRadioButton rbDesempregado, rbEmpregado;

    public TelaPessoa() {
        super("Cadastro de Pessoa");

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

        // Botões e Radio Buttons
        btnCalcular = new JButton("Calcular");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        rbDesempregado = new JRadioButton("Desempregado");
        rbEmpregado = new JRadioButton("Empregado");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbDesempregado);
        grupo.add(rbEmpregado);

        // Painel de entrada
        JPanel painelEntrada = new JPanel(new GridLayout(9, 2));
        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(txtNome);
        painelEntrada.add(new JLabel("Idade:"));
        painelEntrada.add(txtIdade);
        painelEntrada.add(rbDesempregado);
        painelEntrada.add(rbEmpregado);
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
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            PessoaFisica pessoa = null;

            // Verifica se é desempregado ou empregado
            if (rbDesempregado.isSelected()) {
                pessoa = new Desempregado();
            } else if (rbEmpregado.isSelected()) {
                // Verifica o tipo de empregado
                if (!txtSalarioMensal.getText().isEmpty()) {
                    Mensalista mensalista = new Mensalista();
                    mensalista.setSalarioMensal(Double.parseDouble(txtSalarioMensal.getText()));
                    pessoa = mensalista;
                } else if (!txtComissao.getText().isEmpty()) {
                    Comissionado comissionado = new Comissionado();
                    comissionado.setComissao(Double.parseDouble(txtComissao.getText()));
                    pessoa = comissionado;
                } else if (!txtHoras.getText().isEmpty() && !txtValorHora.getText().isEmpty()) {
                    Horista horista = new Horista();
                    horista.setHorasTrabalhadas(Double.parseDouble(txtHoras.getText()));
                    horista.setValorHora(Double.parseDouble(txtValorHora.getText()));
                    pessoa = horista;
                } else if (!txtValorTarefa.getText().isEmpty()) {
                    Tarefeiro tarefeiro = new Tarefeiro();
                    tarefeiro.setValorTarefa(Double.parseDouble(txtValorTarefa.getText()));
                    pessoa = tarefeiro;
                }
            }

            // Exibe informações
            if (pessoa != null) {
                pessoa.setNome(txtNome.getText());
                pessoa.setIdade(Integer.parseInt(txtIdade.getText()));
                if (pessoa instanceof Empregado) {
                    txtSalario.setText(String.format("%.2f", ((Empregado) pessoa).calcularSalario()));
                    JOptionPane.showMessageDialog(this, pessoa.toString() + "\nSalário: " + txtSalario.getText(), "Informações da Pessoa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    txtSalario.setText("N/A");
                    JOptionPane.showMessageDialog(this, pessoa.toString(), "Informações da Pessoa", JOptionPane.INFORMATION_MESSAGE);
                }
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
            rbDesempregado.setSelected(false);
            rbEmpregado.setSelected(false);
        } else if (e.getSource() == btnSair) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new TelaPessoa();
    }
}

