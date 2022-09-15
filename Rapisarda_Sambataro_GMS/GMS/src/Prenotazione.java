import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

public class Prenotazione {

    int idPrenotazione;
    private Date data;
    private Cliente cliente;
    private RigaCatalogo articolo;
    private String ricevuta;

    public Prenotazione(RigaCatalogo r, Cliente c){
        idPrenotazione=getIDprenotazione();
        data= new Date();
        cliente=c;
        articolo=r;
        ricevuta=createRicevuta();
        try{
            BufferedWriter bw= new BufferedWriter(new FileWriter("prenotazioni.txt",true));
            bw.write(ricevuta);
            bw.close();
        }catch (Exception e){
            System.out.println("errore");
        }
    }

    public int getIDprenotazione(){
        try {
            String var="0";
            String prec="0";
            BufferedReader fp = new BufferedReader(new FileReader("prenotazioni.txt"));

            while (var!=null){
                prec=var;
                var=fp.readLine();
            }
            return Integer.parseInt(prec);
        }catch(Exception e){
            return -1;
        }
    }

    public String createRicevuta(){
        String result;
        int nuovoIDprenotazione= idPrenotazione;
        nuovoIDprenotazione++;
        result = ("***** RICEVUTA *****"       + "\n"
                + "idPrenotazione:"      + idPrenotazione      + "\n"
                + "data e ora:"      + data    + "\n"
                + "nome e cognome :" + cliente.getNome() +" "+cliente.getCognome() + "\n"
                + "prodotto prenotato:"   + articolo.toString2()
                + nuovoIDprenotazione      );

        return result;
    }

    public String getRicevuta() {
        return ricevuta;
    }
}
