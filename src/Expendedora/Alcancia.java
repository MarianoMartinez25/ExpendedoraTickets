package Expendedora;

import java.util.ArrayList;
import Monedas.MedioDePago;

public class Alcancia<T extends MedioDePago> extends ArrayList<T> {
 
   public final T tipoDePago(){
       if (size() > 0)
           return this.get(0);
       else
           return null;
   }

   public void insertarMedioDePago(T objActual) {
       this.add(objActual);
   }

   public int totalizar(){
       int t = 0;
       for (T obj : this)
           t += obj.getValor();
       return t;
   }
}
