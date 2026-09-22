# Mini Project 2 PBO – Sistem Pendataan dan Monitoring Satwa Dilindungi

## Latar Belakang

Satwa dilindungi di Indonesia, seperti Orangutan dan Komodo, perlu didata dan dipantau agar keberadaannya tetap terjaga. Pencatatan yang dilakukan secara manual rentan salah atau tercatat ganda. Oleh karena itu, dibuat program sederhana berbasis Java untuk membantu mencatat dan memantau data satwa dilindungi, sebagai pengembangan dari Mini Project 1 dengan menerapkan konsep Pemrograman Berorientasi Objek (PBO).

## Deskripsi Program

Program ini merupakan aplikasi berbasis konsol (Command Line Interface) yang digunakan untuk mengelola data satwa dilindungi. Data yang dikelola meliputi ID satwa, nama, jenis (Mamalia atau Reptil), serta data khusus tiap jenis, yaitu habitat untuk Mamalia dan status berbisa untuk Reptil.

Program menyediakan empat fitur utama:

- **Tambah Satwa** - menambahkan data satwa baru.
- **Tampilkan Satwa** - menampilkan seluruh data satwa yang tersimpan.
- **Update Satwa** - mengubah nama satwa berdasarkan ID.
- **Hapus Satwa** - menghapus data satwa berdasarkan ID.

---

## MVC yang Digunakan

Program ini menerapkan struktur **MVC (Model, View, Controller)** 

### Model
Berisi class Satwa, Mamalia, dan Reptil.
Bagian ini bertugas menyimpan data satwa.

### View
Berisi class Menu.
Bagian ini bertugas menampilkan menu dan menerima pilihan dari pengguna.

### Controller
Berisi class SatwaCRUD dan SatwaCek.
Bagian ini bertugas mengatur proses tambah, tampil, update, hapus data, serta memeriksa input pengguna.

### Main
Berisi class MinproPbo2Satwaygdilindungi.
Bagian ini menjalankan program, yaitu membuat objek Scanner dan SatwaCRUD, lalu memanggil Menu.

**Struktur Package MVC**

<img width="327" height="236" alt="image" src="https://github.com/user-attachments/assets/99d072ea-08a3-4b72-ab7d-5d5f928bff7c" />


Gambar di atas menunjukkan struktur package project, yaitu package Model berisi 3 class, package View berisi 1 class, package Controller berisi 2 class, serta package utama yang berisi class main. Pembagian ini menunjukkan bahwa program sudah dipisahkan sesuai perannya masing-masing dalam MVC.

---

# Alur Program

Program dimulai dari method main() pada class MinproPbo2Satwaygdilindungi. Program membuat objek Scanner untuk membaca input, kemudian membuat objek SatwaCRUD. Saat objek SatwaCRUD dibuat, constructor-nya langsung mengisi data awal (dummy data) ke dalam ArrayList.
Selanjutnya, Menu.jalankan() dipanggil dan menampilkan menu utama secara berulang menggunakan while (true), sampai pengguna memilih untuk keluar.

**Menu Utama**

<img width="436" height="245" alt="image" src="https://github.com/user-attachments/assets/ff529953-4f26-423e-a7f0-e2020030a95c" />


Gambar di atas menunjukkan tampilan menu utama saat program dijalankan, yang terdiri dari lima pilihan: Tambah Satwa, Tampilkan Satwa, Update Satwa, Hapus Satwa, dan Keluar.
Pengguna memasukkan angka sesuai pilihan menu. Program menggunakan switch untuk menentukan proses yang dijalankan:

```java
switch (pilihan) {

    case "1":
        crud.tambahSatwa(input);
        break;

    case "2":
        crud.tampilkanSatwa();
        break;

    case "3":
        crud.updateSatwa(input);
        break;

    case "4":
        crud.hapusSatwa(input);
        break;

    case "5":
        System.out.println(">> Siap Boss, program selesai. Sampai jumpa Bosku!");
        return;

    default:
        System.out.println(">> Woopss, menu itu tidak ada Bosku! Pilih 1 sampai 5 ya.");
}
```

Jika pilihan sesuai (1–5), program memanggil method yang sesuai di SatwaCRUD. Jika pilihan tidak sesuai, program menampilkan pesan menu tidak tersedia dan kembali menampilkan menu, tanpa menghentikan program. Ketika pengguna memilih menu 5, perulangan while dihentikan dengan return dan program selesai.

---

# CRUD

Seluruh proses CRUD ditangani oleh class SatwaCRUD yang menyimpan data pada ArrayList<Satwa> bernama daftarSatwa.

## Create tambahSatwa()

**Fungsi:** menambahkan data satwa baru ke dalam ArrayList.

**Proses:** pengguna memasukkan ID, nama, dan jenis satwa. Program mengecek apakah ID sudah dipakai. Setelah jenis dipastikan valid (Mamalia atau Reptil), program meminta data tambahan sesuai jenisnya, lalu membuat objek Mamalia atau Reptil dan menambahkannya ke ArrayList menggunakan add().

```java
public void tambahSatwa(Scanner input) {

    int id = SatwaCek.cekId(input);

    while (idSudahAda(id)) {
        System.out.println(">> Woopss, ID itu sudah dipakai Bosku, coba ID lain!");
        id = SatwaCek.cekId(input);
    }

    String nama = SatwaCek.cekString(input, "Masukkan nama satwa Boss: ");
    String jenis = SatwaCek.cekString(input, "Masukkan jenis satwa Boss (Mamalia/Reptil): ");

    while (!jenis.equalsIgnoreCase("Mamalia") && !jenis.equalsIgnoreCase("Reptil")) {
        System.out.println(">> Woopss, jenis harus Mamalia atau Reptil Bosku!");
        jenis = SatwaCek.cekString(input, "Masukkan jenis satwa Boss (Mamalia/Reptil): ");
    }

    if (jenis.equalsIgnoreCase("Mamalia")) {
        String habitat = SatwaCek.cekString(input, "Masukkan habitat Boss: ");
        daftarSatwa.add(new Mamalia(id, nama, "Mamalia", habitat));
    } else {
        System.out.print(">> Apakah berbisa Boss? (ya/tidak): ");
        boolean berbisa = SatwaCek.cekBoolean(input);
        daftarSatwa.add(new Reptil(id, nama, "Reptil", berbisa));
    }

    System.out.println(">> Mantap Boss, data satwa berhasil ditambahkan!");
}
```

Input yang diberikan pengguna adalah ID, nama, jenis, dan data tambahan (habitat atau berbisa) sesuai jenis yang dipilih. Data yang sudah lengkap dan valid dimasukkan ke ArrayList menggunakan add().

**Proses Tambah Data**

<img width="630" height="157" alt="image" src="https://github.com/user-attachments/assets/3232612a-6ce4-4f03-a64d-d0e9d3f7d921" />


---

## Read tampilkanSatwa()

**Fungsi:** menampilkan seluruh data satwa yang tersimpan di ArrayList.

**Proses:** program mengecek apakah ArrayList kosong. Jika tidak kosong, program mengambil data satu per satu menggunakan for-each dan memanggil tampilkanInfo() pada tiap objek.

```java
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
```
**Proses Tampilkan Data**

<img width="502" height="603" alt="image" src="https://github.com/user-attachments/assets/89a88e87-6526-47e2-b5e0-12984b5753d7" />


---

## Update updateSatwa()

**Fungsi:** mengubah nama satwa berdasarkan ID.

**Proses:** pengguna memasukkan ID satwa yang ingin diubah. Program mencari data dengan ID tersebut menggunakan for-each. Jika ditemukan, pengguna memasukkan nama baru, lalu nama diubah menggunakan setNama().

```java
public void updateSatwa(Scanner input) {

    int id = SatwaCek.cekId(input);

    for (Satwa satwa : daftarSatwa) {

        if (satwa.getId() == id) {

            String nama = SatwaCek.cekString(input, "Masukkan nama baru Boss: ");
            satwa.setNama(nama);

            System.out.println(">> Mantap Boss, data satwa berhasil diupdate!");
            return;
        }
    }

    System.out.println(">> Woopss, ID satwa tidak ditemukan Bosku!");
}
```

Input yang diberikan pengguna adalah ID satwa dan nama baru. Data pada ArrayList tidak dihapus atau dibuat ulang, hanya atribut nama pada objek yang sudah ada yang diubah melalui setter.

**Proses Update Data**

<img width="587" height="118" alt="image" src="https://github.com/user-attachments/assets/d57f2333-a594-443c-bf32-2929811f768e" />

Gambar di atas menunjukkan proses update nama satwa berdasarkan ID beserta pesan berhasil yang ditampilkan program.

---

## Delete hapusSatwa()

**Fungsi:** menghapus data satwa berdasarkan ID.

**Proses:** pengguna memasukkan ID satwa yang ingin dihapus. Program mencari posisi data dengan ID tersebut menggunakan for. Jika ditemukan, data dihapus dari ArrayList menggunakan remove().

```java
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
```

Input yang diberikan pengguna hanya ID satwa. Jika ID ditemukan, objek pada indeks tersebut dihapus dari ArrayList. Jika tidak ditemukan, program menampilkan pesan bahwa ID tidak ditemukan.

**Proses Hapus Data**

<img width="560" height="98" alt="image" src="https://github.com/user-attachments/assets/4bf2c579-7c11-466d-9e9c-6fc57f1dea3c" />


---

# Input Validation

Validasi input pada program ini ditangani oleh class SatwaCek, dengan tujuan agar program tidak berhenti secara tiba-tiba ketika pengguna memasukkan data yang salah.

## Validasi ID

ID harus berupa angka dan lebih dari 0. Validasi ini menggunakan try-catch untuk menangani NumberFormatException, serta while agar pengguna dapat mengulang input jika salah.

```java
public static int cekId(Scanner input) {
    while (true) {
        try {
            System.out.print(">> Masukkan ID Boss: ");
            int id = Integer.parseInt(input.nextLine());

            if (id > 0) {
                return id;
            }

            System.out.println(">> Woopss, ID harus lebih dari 0 Bosku!");
        } catch (NumberFormatException e) {
            System.out.println(">> Woopss, ID harus berupa angka Bosku!");
        }
    }
}
```

Jika pengguna memasukkan input seperti "abc", Integer.parseInt() akan menghasilkan NumberFormatException, sehingga program menampilkan pesan "Woopss, ID harus berupa angka Bosku!" dan meminta ID dimasukkan kembali, tanpa menghentikan program.

**Invalid Input**

<img width="516" height="113" alt="image" src="https://github.com/user-attachments/assets/d296f9da-251a-4b75-9f6b-0e94389dcef5" />


---

## ID Tidak Boleh Sama

Saat menambah data, program mengecek apakah ID yang dimasukkan sudah ada di ArrayList menggunakan for-each di dalam method idSudahAda(). Jika sudah ada, pengguna diminta memasukkan ID lain dengan while.

```java
private boolean idSudahAda(int id) {
    for (Satwa satwa : daftarSatwa) {
        if (satwa.getId() == id) {
            return true;
        }
    }
    return false;
}
```

<img width="606" height="80" alt="image" src="https://github.com/user-attachments/assets/475e809f-6498-4b23-9ce4-68908f742c9c" />

---

## Validasi String Tidak Boleh Kosong

Input berupa nama atau habitat tidak boleh kosong atau hanya berisi spasi. Program menggunakan trim() untuk menghapus spasi di awal dan akhir input, kemudian isEmpty() untuk memastikan input tidak kosong.

```java
public static String cekString(Scanner input, String pesan) {
    while (true) {
        System.out.print(">> " + pesan);
        String data = input.nextLine().trim();

        if (!data.isEmpty()) {
            return data;
        }

        System.out.println(">> Woopss, input tidak boleh kosong Bosku!");
    }
}
```

<img width="547" height="185" alt="image" src="https://github.com/user-attachments/assets/8c99e6af-5b57-4c52-9b6a-27b9b69e9feb" />


---

## Validasi Jenis Satwa

Jenis satwa hanya boleh diisi "Mamalia" atau "Reptil". Validasi ini menggunakan operator ! dan && di dalam while, sehingga pengguna terus diminta mengulang input selama jenis yang dimasukkan bukan keduanya.

```java
while (!jenis.equalsIgnoreCase("Mamalia") && !jenis.equalsIgnoreCase("Reptil")) {
    System.out.println(">> Woopss, jenis harus Mamalia atau Reptil Bosku!");
    jenis = SatwaCek.cekString(input, "Masukkan jenis satwa Boss (Mamalia/Reptil): ");
}
```

equalsIgnoreCase() dipakai agar pengguna tetap bisa memasukkan huruf besar atau kecil, misalnya "mamalia" atau "MAMALIA", dan tetap dianggap valid.

<img width="606" height="157" alt="image" src="https://github.com/user-attachments/assets/802a78da-33d8-4759-81c4-efc087d9674f" />

---

## Validasi Pilihan Ya/Tidak

Untuk data berbisa pada Reptil, pengguna hanya boleh menjawab "ya" atau "tidak". Validasi ini juga menggunakan equalsIgnoreCase() dan while.

```java
public static boolean cekBoolean(Scanner input) {
    while (true) {
        String data = input.nextLine();

        if (data.equalsIgnoreCase("ya")) {
            return true;
        }

        if (data.equalsIgnoreCase("tidak")) {
            return false;
        }

        System.out.println(">> Woopss, jawab 'ya' atau 'tidak' saja Bosku!");
        System.out.print(">> Masukkan pilihan Boss: ");
    }
}
```

<img width="617" height="162" alt="image" src="https://github.com/user-attachments/assets/8007f4f4-e3a0-4717-a9ae-11aeef887ef7" />

---

# Encapsulation

Encapsulation diterapkan pada class Satwa dengan menyembunyikan atribut menggunakan modifier private atau protected, sehingga atribut hanya dapat diakses melalui getter dan setter.

```java
private final int id;
protected String nama;
protected String jenis;

public String getNama() {
    return nama;
}

public void setNama(String nama) {
    this.nama = nama;
}
```

private pada atribut id berarti hanya dapat diakses di dalam class Satwa sendiri. Atribut id juga dibuat final karena ID tidak boleh berubah setelah data dibuat, sehingga tidak disediakan setter untuk atribut ini.

Getter, seperti getNama(), digunakan untuk mengambil nilai atribut dari luar class tanpa mengakses atribut secara langsung.
Setter, seperti setNama(), digunakan untuk mengubah nilai atribut dengan cara yang terkontrol.

Atribut tidak diakses langsung dari class lain agar data lebih aman dan perubahan data hanya dapat dilakukan melalui method yang sudah disediakan. Sebagai contoh, pada proses update, SatwaCRUD mengubah nama satwa dengan memanggil:

```java
satwa.setNama(nama);
```

---

# Inheritance

Program ini memiliki satu superclass (Satwa) dan dua subclass (Mamalia dan Reptil):

```text
Super class Satwa
sub class Mamalia
sub class Reptil
```

Mamalia dan Reptil merupakan subclass dari Satwa menggunakan kata kunci extends, sehingga keduanya mewarisi atribut dan method yang ada pada Satwa, yaitu ID, nama, jenis, beserta getter, setter, dan tampilkanInfo().

### Class Mamalia

```java
public class Mamalia extends Satwa {

    private String habitat;

    public Mamalia(int id, String nama, String jenis, String habitat) {
        super(id, nama, jenis);
        this.habitat = habitat;
    }
}
```

### Class Reptil

```java
public class Reptil extends Satwa {

    private boolean berbisa;

    public Reptil(int id, String nama, String jenis, boolean berbisa) {
        super(id, nama, jenis);
        this.berbisa = berbisa;
    }
}
```

super(id, nama, jenis) digunakan untuk memanggil constructor milik superclass Satwa, sehingga data umum (id, nama, jenis) diisi oleh constructor Satwa, sedangkan data khusus habitat pada Mamalia dan berbisa pada Reptil diisi oleh constructor subclass itu sendiri.

**Struktur Inheritance**

<img width="170" height="77" alt="image" src="https://github.com/user-attachments/assets/3402a1a3-d548-4235-96c0-dbbb56ff1b01" />


Gambar di atas menunjukkan struktur package Model yang berisi class Satwa, Mamalia, dan Reptil, sesuai hierarki inheritance yang digunakan pada program.

---

# Polymorphism dan Method Overriding

Program menerapkan polymorphism melalui method overriding pada method tampilkanInfo().

## Method pada Superclass Satwa

```java
public void tampilkanInfo() {
    System.out.println(">> ID Satwa: " + id);
    System.out.println(">> Nama: " + nama);
    System.out.println(">> Jenis: " + jenis);
}
```

## Override pada Mamalia

```java
@Override
public void tampilkanInfo() {
    super.tampilkanInfo();
    super.cetakStatus();
    System.out.println(">> Habitat: " + habitat);
}
```

## Override pada Reptil

```java
@Override
public void tampilkanInfo() {
    super.tampilkanInfo();
    super.cetakStatus();
    System.out.println(">> Berbisa: " + (berbisa ? "Ya" : "Tidak"));
}
```

Method ini disebut overriding karena Mamalia dan Reptil menulis ulang method tampilkanInfo() yang sudah ada pada superclass Satwa, ditandai dengan anotasi @Override.
Di dalamnya, super.tampilkanInfo()` tetap dipanggil agar informasi dasar dari Satwa (ID, nama, jenis) tetap ditampilkan, kemudian ditambahkan informasi khusus sesuai subclassnya

Perilaku polymorphism terlihat pada method tampilkanSatwa() di SatwaCRUD, di mana ArrayList bertipe Satwa dapat menyimpan objek Mamalia dan Reptil sekaligus:

```java
for (Satwa satwa : daftarSatwa) {
    satwa.tampilkanInfo();
}
```

Meskipun perintah yang dipanggil sama, yaitu `satwa.tampilkanInfo()`, hasil yang ditampilkan berbeda tergantung objek sebenarnya. Jika objeknya Mamalia, maka habitat yang ditampilkan. Jika objeknya Reptil, maka status berbisa yang ditampilkan.

**Hasil Polymorphism overread**

<img width="655" height="130" alt="image" src="https://github.com/user-attachments/assets/4cbb695e-f3fa-4de6-a7df-b0d6e826bd2f" />


<img width="568" height="147" alt="image" src="https://github.com/user-attachments/assets/d684d8dd-7055-4ad7-8c8e-f7f04a90b7f5" />


Gambar di atas menunjukkan hasil pemanggilan tampilkanInfo() pada objek Mamalia dan Reptil, yang menghasilkan tampilan berbeda sesuai jenis satwanya.

---

# Dummy Data

Agar menu Tampilkan tidak langsung kosong saat program pertama kali dijalankan, ArrayList daftarSatwa sudah diisi 2 data awal pada constructor SatwaCRUD:

```java
public SatwaCRUD() {
    daftarSatwa = new ArrayList<>();

    daftarSatwa.add(new Mamalia(1, "Orangutan", "Mamalia", "Hutan"));
    daftarSatwa.add(new Reptil(2, "Komodo", "Reptil", false));
}
```

Dummy data yang digunakan adalah Orangutan sebagai Mamalia dengan habitat Hutan, dan Komodo sebagai Reptil yang tidak berbisa.

**Data Dummy**

<img width="377" height="392" alt="image" src="https://github.com/user-attachments/assets/d43471bd-4c87-4b9c-a54a-04ba34f6df48" />

Gambar di atas menunjukkan tampilan dummy data saat pengguna memilih menu Tampilkan Satwa untuk pertama kali.

---


## Keluar

<img width="620" height="413" alt="image" src="https://github.com/user-attachments/assets/62ddd0d6-0983-42a1-932b-5f5e4375710e" />


---

# Kesimpulan dan Pengembangan dari Minpro 1

Mini Project 2 ini dikembangkan berdasarkan komentar dan masukan pada Mini Project 1, yaitu dengan menambahkan penjelasan validasi input pada README, memperluas validasi input di beberapa bagian program, serta mengembangkan pemodelan data dengan menambahkan class Mamalia dan Reptil sebagai subclass dari Satwa.

Dengan demikian, Mini Project 2 ini sudah menindaklanjuti komentar tersebut sekaligus menerapkan konsep PBO yang lebih lengkap, yaitu input validation, encapsulation, inheritance, polymorphism melalui method overriding, penggunaan ArrayList dengan dummy data, serta struktur MVC. 
