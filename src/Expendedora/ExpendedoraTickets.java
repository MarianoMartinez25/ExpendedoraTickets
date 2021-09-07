package Expendedora;

import java.util.ArrayList;
import Monedas.*;
import java.util.Collection;

public final class ExpendedoraTickets {
   public final Alcancia<MedioDePago> alcancia = new Alcancia();
   public final DepositoDinero caja = new DepositoDinero();
   public final Boletero emisor = new Boletero();
   public final InterfaceDeUsuario.MaquinaDeTickets consola;

   public ExpendedoraTickets(InterfaceDeUsuario.MaquinaDeTickets obj){
       consola = obj;

       Moneda [] tiposDeMoneda = Monedas.Moneda.getMonedas();
       for (Moneda mTipo : tiposDeMoneda){
           ArrayList v  = new ArrayList();
           for (int i = 0; i < 20; i++) // inicializa con 20 monedas de c/a tipo
               v.add(mTipo.clone());
           caja.guardar(v);
       }
   }

   public void acumular(MedioDePago m) {
       alcancia.insertarMedioDePago(m);
   }

   public void entregar(String boleto, int valor) throws ErrorDeCompra {
       int pago = alcancia.totalizar();
       int vuelto = pago - valor;

       if (pago >= valor){
           consola.visor.setText("\npago ->" + pago + " vuelto -> " + vuelto + "\n");
           if (emisor.emitir(boleto, Integer.toString(valor)))
                guardarDinero( alcancia.tipoDePago(), valor, vuelto );
           else {
                 devolver(alcancia, TipoDeError.EMISION_BOLETO);
           }
       }
       else {
           devolver(alcancia, TipoDeError.FALTA_DINERO);
       }
   }

   public void solicitar(Viaje v) throws ErrorDeCompra {
       entregar(v.cdad, v.costo);    
   }

    public void ingresarPago(Monedas.MedioDePago m){
        insertar(m);
        consola.pago.setText("" + alcancia.totalizar());
    }

    public Viaje pedido(){
        int n = consola.ciudades.getSelectedRow();
        String s = consola.ciudades.getValueAt(n, 0).toString();
        int v = new Short(consola.ciudades.getValueAt(n, 1).toString()).shortValue();
        return new Expendedora.Viaje(s, v);
    }

/** Metodos privados */
   
   private void guardarDinero(MedioDePago m, int costo, int vuelto) throws ErrorDeCompra {
       if (m instanceof Tarjeta) {
           Alcancia<Acumulable> a = (Alcancia)alcancia.clone();
           a.set(0, new Ficha(costo));  // genera una ficha del valor correspondiente
           caja.guardar(a);
           ((Tarjeta)m).debitar(costo);
           devolver(alcancia, null);
       }
       else if (m instanceof Moneda || m instanceof Ficha) {
            caja.guardar(alcancia);
            if (vuelto > 0) devolver( calcVuelto(vuelto), null );
       }
       else {
            devolver(alcancia, TipoDeError.GUARDAR_DINERO);           
       }
   }
   
   private Alcancia calcVuelto(int dif) {
       Alcancia<Moneda> vuelto = new Alcancia();
       int ctas, k = 0;
       if (dif > 0){
           for (Moneda cadaMoneda : Moneda.getMonedas()){
               ctas = cadaMoneda.ctasDeVuelto(dif);
               for (Moneda m : caja.extraer(cadaMoneda, ctas))
                   vuelto.add(m);
               dif -= ctas * cadaMoneda.getValor();
           }
           return vuelto;
       }
       else
           return null;
   }

   private void insertar(MedioDePago m) {
       acumular(m);
   }
 
   public void devolver(Alcancia<MedioDePago> unaAlcancia, TipoDeError t) throws ErrorDeCompra {
       int i = 0;
       String s = consola.visor.getText();
       for (MedioDePago obj : unaAlcancia)
           s += ( "> " + obj.getClass().getName() + "\n" );
       consola.visor.setText(s);
       if (!alcancia.removeAll(alcancia)) throw new ErrorDeCompra(t, consola);
   }

}
