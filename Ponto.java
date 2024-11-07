public class Ponto{
   private double coordenadaX, coordenadaY;

   public Ponto(){
   coordenadaX = 0.0;
   coordenadaY = 0.0;
   }
   public Ponto(double x, double y){
   coordenadaX = x;
   coordenadaY = y;
   }
   public double getCoordenadaX(){
   return coordenadaX;
   }
   public double getCoordenadaY(){
   return coordenadaY;
   }
   public void setCoordenadaX(double x){
   coordenadaX = x;
   }
   public void setCoordenadaY(double y){
   coordenadaY = y;
   }
   public String toString(){
   return "Ponto:[coordenadaX="+coordenadaX+
   "][coordenadaY="+coordenadaY+"]";
   }
}