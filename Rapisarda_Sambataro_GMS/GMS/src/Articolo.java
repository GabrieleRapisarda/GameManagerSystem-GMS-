public class Articolo {

    private int id;
    private String titolo;
    private String piattaforma;
    private double prezzo;

    public Articolo(){

    }
    public Articolo(int id, String t, String p, double pr){
        this.id=id;
        titolo=t;
        piattaforma=p;
        prezzo=pr;
    }

    public String toString() {
        String result;

        result = ("id:" + id + "\n"
                + "titolo:" + titolo + "\n"
                + "piattaforma:" + piattaforma + "\n"
                + "prezzo:" + prezzo + "\n");
        return result;
    }

    public String toString2() {
        String result;

        result = (id + "\n"
                +  titolo + "\n"
                + piattaforma + "\n"
                +  prezzo + "\n");
        return result;
    }

    public String getTitolo(){
        return titolo;
    }

    public String getPiattaforma(){
        return piattaforma;
    }

    public double getPrezzo(){
        return prezzo;
    }

    public int getId() {
        return id;
    }
}
