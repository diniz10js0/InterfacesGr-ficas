public class Cilindro extends Circulo{
   private double altura;

   public Cilindro(){
   altura = 1.0;
   }
   public Cilindro(double a, double r, double x, double y){
   super(r,x,y);
   setAltura(a);
   }
   public double getAltura(){
   return altura;
   }
   
   public void setAltura(double a){
   if(a >= 0){
   altura = a;
      }
   }
   public double volume(){
   return area()*altura;
   }
   public double areaDaSuperficieExterna(){
   return (2*area())+(perimetro()*altura);
   }
   public String toString(){
   return "Cilindro:[altura="+altura+"]"+super.toString();
   }
}