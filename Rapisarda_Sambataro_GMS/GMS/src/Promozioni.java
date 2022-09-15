import java.io.*;
import java.util.LinkedList;

public class Promozioni extends Promozione{

    private LinkedList<Promozione> listaPromozioni;

    public Promozioni(){
        listaPromozioni= new LinkedList<Promozione>();
    }

    public void addNewPromozione(int id,String nome, int p){

        listaPromozioni.add(new Promozione(id,nome,p));
    }

    public void deletePromozione(int id){
        for(Promozione p : listaPromozioni){
            if (p.getIdPromozione()==id)
                listaPromozioni.remove(p);
        }
    }


    public void updatePromozione(int id,int newParam){
        for(Promozione p : listaPromozioni){
            if (p.getIdPromozione()==id)
                p.setPercentualePromozione(newParam);
        }
    }
    public int getPercentuale(int id){
        for(Promozione p : listaPromozioni){
            if (p.getIdPromozione()==id)
                return p.getPercentualePromozione();
        }
        return 0;
    }

    public void caricaPromozioni(){
        int id;
        String nome;
        int p;

        try{
            BufferedReader fp=new BufferedReader(new FileReader("promozioni.txt"));
            id=Integer.parseInt(fp.readLine());
            while(id!=0){
                nome=fp.readLine();
                p=Integer.parseInt(fp.readLine());
                listaPromozioni.add(new Promozione(id,nome,p));
                id=Integer.parseInt(fp.readLine());
            }

        }catch(IOException e){
            System.out.println("errore nel caricamento dati caricaPromozioni");
            System.exit(-3);
        }
    }

    public String toString(){

        String result;
        result="***** promozioni *****" + "\n";
        for (Promozione p : listaPromozioni){
            result+=p;
        }
        result+="--------------------\n";
        return result;
    }

    public void terminate(){
        try {
            PrintWriter writer = new PrintWriter("promozioni.txt");
            writer.print("");
            writer.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter("promozioni.txt", false));
            for (Promozione p : listaPromozioni) {
                bw.write(p.toString2());
            }
            bw.write("0" + "\n");
            bw.close();
        }catch (Exception e){
            System.out.println("errore in terminatePromozioni");
        }
    }

    public LinkedList<Promozione> getListaPromozioni() {
        return listaPromozioni;
    }
}
