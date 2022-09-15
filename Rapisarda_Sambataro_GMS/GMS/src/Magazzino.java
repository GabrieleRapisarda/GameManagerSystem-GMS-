import java.io.*;

import java.util.*;

public class Magazzino  {

    LinkedList<Copia> listaCopie;
    HashMap<Integer, Integer> listaDisp;
    GMS gms;


    public Magazzino(){
        listaCopie = new LinkedList<Copia>();
        listaDisp  = new HashMap<Integer, Integer>();
    }

    public void caricaCopie(){
        int id;
        String titolo;
        String piattaforma;
        double prezzo;
        int idcopia;

        try{
            BufferedReader fp=new BufferedReader(new FileReader("copieMagazzino.txt"));
            id=Integer.parseInt(fp.readLine());
            while(id!=0){
                titolo=fp.readLine();
                piattaforma=fp.readLine();
                prezzo=Double.parseDouble(fp.readLine());
                idcopia=Integer.parseInt(fp.readLine());
                listaCopie.add(new Copia(idcopia,id,titolo,piattaforma,prezzo));
                id=Integer.parseInt(fp.readLine());
            }

        }catch(IOException e){
            System.out.println("errore nel caricamento dati listaSale");
            System.exit(-3);
        }

    }


    public Copia getArticolo(String t, String p){
        for (Copia c : listaCopie){
            if(c.getTitolo().equals(t) && c.getPiattaforma().equals(p))
                return c;
        }
        return null;
    }

    public void addCopia(int idcopia, int id, String titolo, String piattaforma, double prezzo){
        listaCopie.add(new Copia(idcopia,id,titolo,piattaforma,prezzo));
        int temp=listaDisp.get(id);
        temp++;
        listaDisp.put(id,temp);
        gms.notify(id,temp);
    }

    public void aggiornaMagazzino(LinkedList<Copia> listaCarrello){
        for (Copia c : listaCarrello){
            listaCopie.remove(c);
            int temp=listaDisp.get(c.getId());
            temp--;
            listaDisp.put(c.getId(),temp);
            gms.notify(c.getId(),temp);
        }
    }

    public void aggiornaMagazzino2(String t, String p, int id){
        for (Copia c : listaCopie){
            if(c.getIdentificativoCopia()==id && c.getTitolo().equals(t) && c.getPiattaforma().equals(p)){
                listaCopie.remove(c);
                int temp=listaDisp.get(c.getId());
                temp--;
                listaDisp.put(c.getId(),temp);
                gms.notify(c.getId(),temp);
            }
        }
    }


    public void aggiornaHashmap(LinkedList<RigaCatalogo> cata){
        for(RigaCatalogo r : cata){
            int nc=0;
            for (Copia c: listaCopie){
                if(r.getId()==c.getId())
                    nc++;
            }
            if(nc>0)
                listaDisp.put(r.getId(),nc);
            else
                listaDisp.put(r.getId(),0);
        }
    }



    public HashMap<Integer,Integer> register(){
        gms= GMS.getInstance();
        return listaDisp;
    }

    public void updateCopia(String t, String p, int idCvecchio ){
        for(Copia c: listaCopie){
            if(c.getIdentificativoCopia()==idCvecchio && c.getTitolo().equals(t) && c.getPiattaforma().equals(p)){
                try{
                    BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("inserire il nuovo identificativo della copia da modificare: ");
                    int idCnuovo = Integer.parseInt(bf.readLine());
                    c.setIdentificativoCopia(idCnuovo);
                }catch(Exception e){
                    System.out.println("Errore nella funzione: updateCopia");
                }
            }
        }

    }

    public String toString(){

        String result;
        result="***** magazzino *****" + "\n";
        for (Copia c : listaCopie){
            result+=c;
        }
        result+="--------------------\n";
        return result;
    }


    public void terminate(){
        try {
            PrintWriter writer = new PrintWriter("copieMagazzino.txt");
            writer.print("");
            writer.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter("copieMagazzino.txt", false));
            for (Copia c : listaCopie) {
                bw.write(c.toString2());
                bw.write(""+c.getIdentificativoCopia()+ "\n");
            }
            bw.write("0" + "\n");
            bw.close();
        }catch (Exception e){
            System.out.println("errore in terminatePromozioni");
        }
    }


}
