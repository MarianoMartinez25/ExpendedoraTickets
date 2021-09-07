package Expendedora;
import Monedas.Moneda;
import Monedas.Acumulable;
import java.util.ArrayList;

public final class DepositoDinero {
   public final Alcancia<Acumulable> alcancia = new Alcancia();

   public void guardar(ArrayList m){
       alcancia.addAll(m);
   }
   
   public <T extends Moneda> T[] extraer(T obj, int n){
       Moneda v[] = new Moneda [n];
       int k;
       for (int i = 0; i < n; i++){
           for (Acumulable  m : alcancia){
               if ( m.getClass() == obj.getClass()){
                   k = alcancia.indexOf(m);
                   v[i] = (T)alcancia.remove(k);
                   break;
               }
           }
       }
       return (T [])v;
   }
}
