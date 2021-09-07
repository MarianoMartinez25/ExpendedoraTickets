package Expendedora;

enum TipoDeErrorPago {
    DUPLICA_TARJETA, MEZCLA_MONEDA_FICHA, MEZCLA_TARJETA_FICHA, MONEDA_FALSA
}

public class ErrorDePago extends Throwable {
    private TipoDeErrorPago t = TipoDeErrorPago.MONEDA_FALSA;
    
    public ErrorDePago(Monedas.MedioDePago o1, Monedas.MedioDePago o2){
        if (o1 instanceof Monedas.Tarjeta && o2 instanceof Monedas.Tarjeta) 
            t = TipoDeErrorPago.DUPLICA_TARJETA;
        else if (o1 instanceof Monedas.Tarjeta && o2 instanceof Monedas.Ficha || 
                o1 instanceof Monedas.Ficha && o2 instanceof Monedas.Tarjeta) 
            t = TipoDeErrorPago.MEZCLA_TARJETA_FICHA;
        else if (o1 instanceof Monedas.Ficha && o2 instanceof Monedas.Moneda || 
                o2 instanceof Monedas.Ficha && o1 instanceof Monedas.Moneda) 
            t = TipoDeErrorPago.MEZCLA_MONEDA_FICHA;
    }

    public String cualError(){
        String s;
        switch (t) {
            case DUPLICA_TARJETA : s = "\nERROR: No puede ingresar dos tarjetas";
            case MEZCLA_MONEDA_FICHA : s = "\nERROR: Mezcla fichas y monedas";
            case MEZCLA_TARJETA_FICHA : s = "\nERROR: Mezcla tarjeta con fichas";
            default : s = "\nERROR: La moneda es falsa";
        }
        return s;
    }
}
