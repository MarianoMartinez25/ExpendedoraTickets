package Monedas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class monedaComparator implements Comparator {
    public int compare(Object m1, Object m2){
        if (((Moneda)m1).getValor() > ((Moneda)m2).getValor()) 
            return -1;         // para ordenamiento inverso
        else if (((Moneda)m2).getValor() < ((Moneda)m1).getValor()) 
            return 1;
        else 
            return 0;
    }
}

public abstract class Moneda implements Acumulable, Cloneable {
    /** Tipos de monedas con que opera la expendedora */
    protected static final ArrayList<Moneda> tiposMonedas = new ArrayList<Moneda>();
    /** Para usar una nueva moneda: 
    * Crear la clase en el package Monedas
    * Agregar el item en la lista desplegable ComboBox
    * Agregar el objeto en tiposMonedas en el inicializador static ->
    */
    static { 
        tiposMonedas.add(new Monedas.M25());
        tiposMonedas.add(new Monedas.M50());
        tiposMonedas.add(new Monedas.M10());
    }

    /** Duplicador de Monedas */
    public Object clone(){ 
        try {
            return super.clone();
        } catch (CloneNotSupportedException e){
            return null;
        }
    }

    /** devuelve un arreglo ordenado de mayor a menor de tiposMonedas
     * por ejemplo para calcular vueltos */
    public static Moneda [] getMonedas(){
        Collections.sort(tiposMonedas, new monedaComparator()); 
        Moneda v[] = new Moneda[tiposMonedas.size()];
        tiposMonedas.toArray(v);
        return v;
    }

    public final int ctasDeVuelto(int v){
        return v / getValor();
    }
}
