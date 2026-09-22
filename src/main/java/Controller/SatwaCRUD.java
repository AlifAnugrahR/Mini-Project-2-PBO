package Controller;

import Model.Mamalia;
import Model.Reptil;
import Model.Satwa;
import java.util.ArrayList;
import java.util.Scanner;

public class SatwaCRUD {

    private ArrayList<Satwa> daftarSatwa;

    public SatwaCRUD() {
        daftarSatwa = new ArrayList<>();

        daftarSatwa.add(new Mamalia(
                1,
                "Orangutan",
                "Mamalia",
                "Hutan"
        ));

        daftarSatwa.add(new Reptil(
                2,
                "Komodo",
                "Reptil",
                false
        ));
    }

    private boolean idSudahAda(int id) {
        for (Satwa satwa : daftarSatwa) {
            if (satwa.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void tambahSatwa(Scanner input) {

        int id = SatwaCek.cekId(input);

        while (idSudahAda(id)) {
            System.out.println(">> Woopss, ID itu sudah dipakai Bosku, coba ID lain!");
            id = SatwaCek.cekId(input);
        }

        String nama = SatwaCek.cekString(
                input,
                "Masukkan nama satwa Boss: "
        );

        String jenis = SatwaCek.cekString(
                input,
                "Masukkan jenis satwa Boss (Mamalia/Reptil): "
        );

        while (!jenis.equalsIgnoreCase("Mamalia") && !jenis.equalsIgnoreCase("Reptil")) {
            System.out.println(">> Woopss, jenis harus Mamalia atau Reptil Bosku!");
            jenis = SatwaCek.cekString(
                    input,
                    "Masukkan jenis satwa Boss (Mamalia/Reptil): "
            );
        }

        if (jenis.equalsIgnoreCase("Mamalia")) {

            String habitat = SatwaCek.cekString(
                    input,
                    "Masukkan habitat Boss: "
            );

            daftarSatwa.add(
                    new Mamalia(id, nama, "Mamalia", habitat)
            );

        } else {

            System.out.print(">> Apakah berbisa Boss? (ya/tidak): ");
            boolean berbisa = SatwaCek.cekBoolean(input);

            daftarSatwa.add(
                    new Reptil(id, nama, "Reptil", berbisa)
            );
        }

        System.out.println(">> Mantap Boss, data satwa berhasil ditambahkan!");
    }

    public void tampilkanSatwa() {

        if (daftarSatwa.isEmpty()) {
            System.out.println(">> Woopss, data satwa masih kosong Bosku!");
            return;
        }

        System.out.println("\n>> ===== DAFTAR SATWA BOSKU =====");

        for (Satwa satwa : daftarSatwa) {
            satwa.tampilkanInfo();
            System.out.println(">> -------------------------");
        }
    }

    public void updateSatwa(Scanner input) {

        int id = SatwaCek.cekId(input);

        for (Satwa satwa : daftarSatwa) {

            if (satwa.getId() == id) {

                String nama = SatwaCek.cekString(
                        input,
                        "Masukkan nama baru Boss: "
                );

                satwa.setNama(nama);

                System.out.println(">> Mantap Boss, data satwa berhasil diupdate!");
                return;
            }
        }

        System.out.println(">> Woopss, ID satwa tidak ditemukan Bosku!");
    }

    public void hapusSatwa(Scanner input) {

        int id = SatwaCek.cekId(input);

        for (int i = 0; i < daftarSatwa.size(); i++) {

            if (daftarSatwa.get(i).getId() == id) {

                daftarSatwa.remove(i);

                System.out.println(">> Siap Boss, data satwa berhasil dihapus!");
                return;
            }
        }

        System.out.println(">> Woopss, ID satwa tidak ditemukan Bosku!");
    }
}