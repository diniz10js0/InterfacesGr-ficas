public class Teste{
   public static void main(String[] args){
   Ponto ponto = new Ponto();
   System.out.println(ponto.toString());
   Circulo circulo = new Circulo();
   System.out.println(circulo.toString());
   circulo = new Circulo(3.0, -1.0, 2.0);
   System.out.println(circulo.toString());
   Cilindro cilindro = new Cilindro(4.0, 3.0, -1.0, 2.0);
   System.out.println(cilindro.toString());
   }
}