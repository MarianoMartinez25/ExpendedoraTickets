package Expendedora;

import java.io.*;

public final class Boletero {
    public boolean emitir(String b, String p) {
        Ticket t = new Ticket(b, p);
        ObjectOutputStream outFile; 
        String nmb = "c:\\Temp\\" + t.destino + t.precio + ".txt";
        try {
            outFile = new ObjectOutputStream(new FileOutputStream(nmb));
            outFile.writeObject((Object)t);
            outFile.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}
