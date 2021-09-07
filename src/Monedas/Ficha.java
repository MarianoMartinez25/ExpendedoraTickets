package Monedas;
/**
 * Los objetos Ficha son un MedioDePago, pero Calificado, porque a diferencia
 * de los objetos Moneda (M10, M25, etc.) que tienen asociado un valor
 * monetario intrinseco, dos objetos Ficha pueden representar valores monetarios
 * diferentes
 */

public final class Ficha extends Calificado implements Acumulable {
   public Ficha(int v){ 
       super(v);
   }
}
