public class RigaCatalogo extends Articolo{

    private boolean disponibilità;

    public RigaCatalogo(int id, String t, String p, double pr){
        super(id,t,p,pr);
        disponibilità=false;
    }

    public String toString() {
        return (super.toString() + "disponibilità:" + disponibilità + "\n");
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public void setDisponibilità(boolean disponibilità) {
        this.disponibilità = disponibilità;
    }

    @Override
    public String getTitolo() {
        return super.getTitolo();
    }

    @Override
    public String getPiattaforma() {
        return super.getPiattaforma();
    }

    public String toString2() {
        String result;

        result = ("id:" + super.getId() + "\n"
                + "titolo:" + super.getTitolo() + "\n"
                + "piattaforma:" + super.getPiattaforma() + "\n"
                + "prezzo:" + super.getPrezzo() + "\n");
        return result;
    }

    public boolean isDisponibilità() {
        return disponibilità;
    }
}
