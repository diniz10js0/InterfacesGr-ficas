import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tela extends JFrame implements ActionListener {
   private JTextField txtCoordenadaX, txtCoordenadaY, txtRaio, txtAltura,
           txtArea, txtPerimetro, txtVolume, txtSuperficie;
   private JLabel lblCoordenadaX, lblCoordenadaY, lblRaio, lblAltura, lblArea,
           lblPerimetro, lblVolume, lblSuperficie;
   private JButton btnCalcula, btnLimpa, btnSai;
   
   public Tela() {
      super("Formas");
      
   // montando o painel dos dados de entrada
   JPanel painelDadosEntrada = new JPanel(new GridLayout(4, 2));
   JPanel painelEntrada = new JPanel(new FlowLayout());
   txtCoordenadaX = new JTextField(10);
   txtCoordenadaY = new JTextField(10);
   txtRaio = new JTextField(10);
   txtAltura = new JTextField(10);
   lblCoordenadaX = new JLabel("CoordenadaX:");
   lblCoordenadaY = new JLabel("CoordenadaY:");
   lblAltura = new JLabel("Altura:");
   lblRaio = new JLabel("Raio:");
   painelDadosEntrada.add(lblCoordenadaX);
   painelDadosEntrada.add(txtCoordenadaX);
   painelDadosEntrada.add(lblCoordenadaY);
   painelDadosEntrada.add(txtCoordenadaY);
   painelDadosEntrada.add(lblRaio);
   painelDadosEntrada.add(txtRaio);
   painelDadosEntrada.add(lblAltura);
   painelDadosEntrada.add(txtAltura);
   painelEntrada.add(painelDadosEntrada);
   // montando o painel dos dados de saida
   JPanel painelDadosSaida = new JPanel(new GridLayout(4, 2));
   JPanel painelSaida = new JPanel(new FlowLayout());
   lblArea = new JLabel("Area do Circulo: ");
   txtArea = new JTextField(10);
   lblPerimetro = new JLabel("Perimetro do Circulo: ");
   txtPerimetro = new JTextField(10);
   lblVolume = new JLabel("Volume do Cilindro: ");
   txtVolume = new JTextField(10);
   lblSuperficie = new JLabel("Area de Superficie do Cilindro: ");
   txtSuperficie = new JTextField(10);
   painelDadosSaida.add(lblArea);
   painelDadosSaida.add(txtArea);
   painelDadosSaida.add(lblPerimetro);
   painelDadosSaida.add(txtPerimetro);
   painelDadosSaida.add(lblVolume);
   painelDadosSaida.add(txtVolume);
   painelDadosSaida.add(lblSuperficie);
   painelDadosSaida.add(txtSuperficie);
   painelSaida.add(painelDadosSaida);
   // montando o painel dos botoes
   JPanel painelBotoes = new JPanel(new FlowLayout());
   btnCalcula = new JButton("Calcular");
   btnLimpa = new JButton("Limpar");
   btnSai = new JButton("Sair");
   painelBotoes.add(btnCalcula);
   painelBotoes.add(btnLimpa);
   painelBotoes.add(btnSai);
   
   // montando a tela toda
   Container caixa = getContentPane();
   caixa.setLayout(new BorderLayout());
   caixa.add(painelEntrada, BorderLayout.WEST);
   caixa.add(painelSaida, BorderLayout.EAST);
   caixa.add(painelBotoes, BorderLayout.SOUTH);
   
   // atribuir listener aos botoes
   btnCalcula.addActionListener(this);
   btnLimpa.addActionListener(this);
   btnSai.addActionListener(this);
   
   // arremates finais
   setSize(710, 190);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setLocationRelativeTo(null);
   setVisible(true);
}

public void actionPerformed(ActionEvent e) {
   // depois iremos aprender a modularizar melhor
   if (e.getSource() == btnCalcula) {
         String coordenadaX = txtCoordenadaX.getText();
   String coordenadaY = txtCoordenadaY.getText();
   String raio = txtRaio.getText();
   String altura = txtAltura.getText();
   if (coordenadaX.length() > 0 && coordenadaY.length() > 0
            && raio.length() > 0 && altura.length() > 0) {
         // e um cilindro
         Cilindro cilindro = new Cilindro();
         cilindro.setCoordenadaX(Double.parseDouble(coordenadaX));
         cilindro.setCoordenadaY(Double.parseDouble(coordenadaY));
         cilindro.setRaio(Double.parseDouble(raio));
         cilindro.setAltura(Double.parseDouble(altura));
         // calcular usando a heranca
         txtArea.setText("" + cilindro.area());
         txtPerimetro.setText("" + cilindro.perimetro());
         txtVolume.setText("" + cilindro.volume());
         txtSuperficie.setText("" + cilindro.areaDaSuperficieExterna());
         // estou usando o this em vez de null para travar a tela de
         // baixo enquanto nao fechar o dialogo
         JOptionPane.showMessageDialog(this, cilindro, "Formas",
            JOptionPane.INFORMATION_MESSAGE);
       } else if (coordenadaX.length() > 0 && coordenadaY.length() > 0
                 && raio.length() > 0) {
            // e um circulo
            Circulo circulo = new Circulo();
            circulo.setCoordenadaX(Double.parseDouble(coordenadaX));
            circulo.setCoordenadaY(Double.parseDouble(coordenadaY));
            circulo.setRaio(Double.parseDouble(raio));
            // calcular
            txtArea.setText("" + circulo.area());
            txtPerimetro.setText("" + circulo.perimetro());
            txtVolume.setText("");
            txtSuperficie.setText("");
            JOptionPane.showMessageDialog(this, circulo, "Formas",
                   JOptionPane.INFORMATION_MESSAGE);
         } else if (coordenadaX.length() > 0 && coordenadaY.length() > 0) {
            // e um ponto
            Ponto ponto = new Ponto();
            ponto.setCoordenadaX(Double.parseDouble(coordenadaX));
            ponto.setCoordenadaY(Double.parseDouble(coordenadaY));
            txtArea.setText("");
            txtPerimetro.setText("");
            txtVolume.setText("");
            txtSuperficie.setText("");
            JOptionPane.showMessageDialog(this, ponto, "Formas",
                   JOptionPane.INFORMATION_MESSAGE);
      } else {
            JOptionPane.showMessageDialog(this, "Dados invalidos.",
            "Formas", JOptionPane.ERROR_MESSAGE);
      }
} else if (e.getSource() == btnLimpa) {
      txtCoordenadaX.setText("");
      txtCoordenadaY.setText("");
      txtRaio.setText("");
      txtAltura.setText("");
      txtArea.setText("");
      txtPerimetro.setText("");
      txtVolume.setText("");
      txtSuperficie.setText("");
} else {
       System.exit(0);
      }
   }
}