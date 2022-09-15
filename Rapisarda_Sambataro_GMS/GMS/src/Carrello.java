import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.Locale;

public class Carrello {

    private int id;
    private Date data;
    private double totale;
    private Cliente cliente;
    LinkedList<Copia> listaCarrello;

    public Carrello(){
        try {
            BufferedReader fp = new BufferedReader(new FileReader("progressivoRicevute.txt"));
            this.id = Integer.parseInt(fp.readLine());
            data = new Date();
            totale = 0;
            listaCarrello= new LinkedList<Copia>();
        }catch (Exception e){
            System.out.println("errore");
        }
    }

    public void setTotale(double t){
        totale=t;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String stampaRicevuta(){
        String result;

        result = ("***** RICEVUTA *****"       + "\n"
                + "idRicevuta:"      + id      + "\n"
                + "data e ora:"      + data    + "\n"
                + "nome e cognome :" + cliente.getNome() +" "+cliente.getCognome() + "\n"
                + "prodotti acquistati:"                    + "\n");
        for(Copia c : listaCarrello)
            result+=(c.toString());
        result+=("totale: "          + totale  + "\n");
        try{
            id++;
            BufferedWriter bw= new BufferedWriter(new FileWriter("progressivoRicevute.txt",false));
            bw.write(""+id);
            bw.close();
        }catch(Exception e){

        }
        return result;
    }

    public double getTotale() {
        return totale;
    }
}
