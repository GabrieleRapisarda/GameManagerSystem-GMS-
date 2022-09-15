public class Copia extends Articolo{

    private int identificativoCopia;

    public Copia(int n, int id, String t, String p, double pr){
        super(id,t,p,pr);
        identificativoCopia=n;

    }

    public String toString() {
        String result;

        result = (identificativoCopia      + "\n"
                  +super.toString2() + "\n");
        return result;
    }

    public String getTitolo(){
        return super.getTitolo();
    }

    public String getPiattaforma(){
        return super.getPiattaforma();
    }

    @Override
    public double getPrezzo() {
        return super.getPrezzo();
    }

    public boolean equals(Object o){
        return(identificativoCopia==((Copia)o).identificativoCopia);
    }

    public int getIdentificativoCopia() {
        return identificativoCopia;
    }

    public void setIdentificativoCopia(int id){
        identificativoCopia=id;
    }

}
