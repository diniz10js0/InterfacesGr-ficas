public class Circulo extends Ponto{
   private double raio;
   
   public Circulo(){
   super();
   raio = 1.0;
   }
   public Circulo(double r, double x, double y){
   super(x,y);
   setRaio(r);
   }
   public void setRaio(double r){
   if(r >= 0){
   raio = r;
     }
   }
   public double getRaio(){
   return raio;
   }
   public double diametro(){
   return 2*raio;
   }
   public double perimetro(){
   return 2*Math.PI*raio;
   }
   public double area(){
   return Math.PI*raio*raio;
   }
   public String toString(){
   return "Circulo:[raio="+raio+"]"+super.toString();
   }
}