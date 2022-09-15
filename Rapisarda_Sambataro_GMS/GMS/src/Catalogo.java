import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Catalogo {


    LinkedList<RigaCatalogo> cat;


    public Catalogo(){
        cat = new LinkedList<RigaCatalogo>();
    }

    public LinkedList<RigaCatalogo> getListaCatalogo(){
        return cat;
    }



    public void caricaCatalogo(){

        int id;
        String titolo;
        String piattaforma;
        double prezzo;

        try{
            BufferedReader fp=new BufferedReader(new FileReader("catalogo.txt"));
            id=Integer.parseInt(fp.readLine());
            while(id!=0){
                titolo=fp.readLine();
                piattaforma=fp.readLine();
                prezzo=Double.parseDouble(fp.readLine());
                cat.add(new RigaCatalogo(id,titolo,piattaforma,prezzo));
                id=Integer.parseInt(fp.readLine());
            }

        }catch(IOException e){
            System.out.println("errore nel caricamento dati Catalogo");
            System.exit(-3);
        }
    }



    public void caricaDisp(HashMap<Integer,Integer> map){
        for(RigaCatalogo c: cat)
            if(map.get(c.getId())>0)
                c.setDisponibilità(true);

    }

    public void update(int id, int nc){
        for(RigaCatalogo r: cat){
            if(r.getId()==id){
                if(nc==0)
                    r.setDisponibilità(false);
                if(nc>0)
                    r.setDisponibilità(true);
            }

        }
    }

    public String toString(){
        String result;
        result="***** CATALOGO *****" + "\n";
        for (RigaCatalogo r : cat){
            result+=r;
        }
        result+="--------------------\n";
        return result;
    }

    public RigaCatalogo findArticolo( String tit, String piatt){
        for (RigaCatalogo r : cat){
            if(r.getTitolo().equals(tit) && r.getPiattaforma().equals(piatt))
                return r;
        }
        return null;
    }


}
