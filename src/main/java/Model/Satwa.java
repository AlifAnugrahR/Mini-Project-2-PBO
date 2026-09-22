package Model;

public class Satwa {

    private final int id;
    protected String nama;
    protected String jenis;

    public Satwa(int id, String nama, String jenis) {
        this.id = id;
        setNama(nama);
        setJenis(jenis);
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void tampilkanInfo() {
        System.out.println(">> ID Satwa: " + id);
        System.out.println(">> Nama: " + nama);
        System.out.println(">> Jenis: " + jenis);
    }

    public final void cetakStatus() {
        System.out.println(">> Status: Satwa Dilindungi");
    }
}