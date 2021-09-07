package Expendedora;

public class ErrorDeCompra extends Throwable {
    public String msje = "ERROR: ";
    public ErrorDeCompra(TipoDeError t, InterfaceDeUsuario.MaquinaDeTickets c){
        switch (t) {
            case FALTA_DINERO: msje += "Falta dinero"; break;
            case EMISION_BOLETO: msje += "No se pudo emitir boleto"; break;
            case VACIADO_ALCANCIA: msje = "No se pudo vaciar alcancia"; break;
            case GUARDAR_DINERO: msje = "No se pudo guardar dinero en la alcancia"; break;
        }
        c.visor.setText(msje);
    }
}
