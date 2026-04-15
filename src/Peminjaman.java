import java.util.Scanner;

public class Peminjaman {
    Mahasiswa mhs;
    Buku buku;
    int lamaPinjam;
    int batasPinjam = 5;
    int terlambat;
    int denda;

    Peminjaman(Mahasiswa mhs, Buku buku, int lamaPinjam) {
        this.mhs = mhs;
        this.buku = buku;
        this.lamaPinjam = lamaPinjam;
        hitungDenda();
    }

    void hitungDenda() {
        if (lamaPinjam > batasPinjam) {
            terlambat = lamaPinjam - batasPinjam;
            denda = terlambat * 2000;
        } else {
            terlambat = 0;
            denda = 0;
        }
    }

    void tampilPeminjaman() {
        System.out.println(mhs.nama + " | " + buku.judul +
                " | Lama: " + lamaPinjam +
                " | Terlambat: " + terlambat +
                " | Denda: " + denda);
    }

    void inputPeminjaman() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan NIM: ");
        String nimInput = sc.next();
        System.out.print("Masukkan Kode Buku: ");
        String kodeInput = sc.next();
        System.out.print("Masukkan Lama Pinjam (hari): ");
        int lamaInput = sc.nextInt();

        Mahasiswa mhsCari = null;
        for (Mahasiswa m : SistemPeminjaman.dataMhs) {
            if (m.nim.equals(nimInput)) {
                mhsCari = m;
                break;
            }
        }

        if (mhsCari == null) {
            System.out.println("NIM tidak ditemukan!");
            return;
        }

        Buku bukuCari = null;
        for (Buku b : SistemPeminjaman.dataBuku) {
            if (b.kodeBuku.equals(kodeInput)) {
                bukuCari = b;
                break;
            }
        }

        if (bukuCari == null) {
            System.out.println("Kode buku tidak ditemukan!");
            return;
        }

        Peminjaman baru = new Peminjaman(mhsCari, bukuCari, lamaInput);

        int panjangLama = SistemPeminjaman.dataPinjam.length;
        Peminjaman[] arrayBaru = new Peminjaman[panjangLama + 1];
        for (int i = 0; i < panjangLama; i++) {
            arrayBaru[i] = SistemPeminjaman.dataPinjam[i];
        }
        arrayBaru[panjangLama] = baru;
        SistemPeminjaman.dataPinjam = arrayBaru;

        System.out.println("Data peminjaman berhasil ditambahkan!");
    }
}