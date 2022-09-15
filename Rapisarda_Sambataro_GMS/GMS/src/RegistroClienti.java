import java.io.*;
import java.util.LinkedList;

public class RegistroClienti {

    LinkedList<Cliente> elencoClienti;

    public RegistroClienti(){
        elencoClienti= new LinkedList<Cliente>();
    }

    public void caricaClienti(){

        String cf;
        String nome;
        String cognome;
        String indirizzo;
        String mail;
        String telefono;

        try{
            BufferedReader fp=new BufferedReader(new FileReader("clienti.txt"));
            cf=fp.readLine();
            while(cf!=null){
                nome=fp.readLine();
                cognome=fp.readLine();
                indirizzo=fp.readLine();
                mail=fp.readLine();
                telefono=fp.readLine();
                elencoClienti.add(new Cliente(cf,nome,cognome,indirizzo,telefono,mail));
                cf=fp.readLine();
            }

        }catch(IOException e){
            System.out.println("errore nel caricamento dati listaSale");
            System.exit(-3);
        }

    }


    public void addNewCliente(Cliente cliente){
        elencoClienti.add(cliente);

        try{
            BufferedWriter bw= new BufferedWriter(new FileWriter("clienti.txt",true));
            bw.write(cliente.toString2());
            bw.close();
        }catch (Exception e){
            System.out.println("errore nella funzione: addNewCliente");
        }
    }





}
