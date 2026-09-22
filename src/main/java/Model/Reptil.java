package Model;

public class Reptil extends Satwa {

    private boolean berbisa;

    public Reptil(int id, String nama, String jenis, boolean berbisa) {
        super(id, nama, jenis);
        this.berbisa = berbisa;
    }

    public boolean isBerbisa() {
        return berbisa;
    }

    public void setBerbisa(boolean berbisa) {
        this.berbisa = berbisa;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        super.cetakStatus();
        System.out.println(">> Berbisa: " + (berbisa ? "Ya" : "Tidak"));
    }
}