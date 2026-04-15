import java.util.Scanner;

public class SistemPeminjaman {

    static Mahasiswa[] dataMhs = {
            new Mahasiswa("22001", "Andi", "Teknik Informatika"),
            new Mahasiswa("22002", "Budi", "Teknik Informatika"),
            new Mahasiswa("22003", "Citra", "Sistem Informasi Bisnis")
    };

    static Buku[] dataBuku = {
            new Buku("B001", "Algoritma", 2020),
            new Buku("B002", "Basis Data", 2019),
            new Buku("B003", "Pemrograman", 2021),
            new Buku("B004", "Fisika", 2024)
    };

    static Peminjaman[] dataPinjam = {
            new Peminjaman(dataMhs[0], dataBuku[0], 7),
            new Peminjaman(dataMhs[1], dataBuku[1], 3),
            new Peminjaman(dataMhs[2], dataBuku[2], 10),
            new Peminjaman(dataMhs[2], dataBuku[3], 6),
            new Peminjaman(dataMhs[0], dataBuku[1], 4)
    };

    // instansiasi data mahasiswa, buku, dan peminjaman
    static void tampilkanMahasiswa() {
        System.out.println("\nDaftar Mahasiswa:");
        for (Mahasiswa m : dataMhs) {
            m.tampilMahasiswa();
        }
    }

    static void tampilkanBuku() {
        System.out.println("\nDaftar Buku:");
        for (Buku b : dataBuku) {
            b.tampilBuku();
        }
    }

    static void tampilkanPeminjaman() {
        System.out.println("\nData Peminjaman:");
        for (Peminjaman p : dataPinjam) {
            p.tampilPeminjaman();
        }
    }

    static void inputPeminjaman() {
        Peminjaman ip = new Peminjaman(dataMhs[0], dataBuku[0], 0);
        ip.inputPeminjaman();
    }

    static void urutkanBerdasarkanDenda() {
        Peminjaman[] temp = dataPinjam.clone();
        int n = temp.length;

        for (int i = 1; i < n; i++) {
            Peminjaman kunci = temp[i];
            int j = i - 1;
            while (j >= 0 && temp[j].denda < kunci.denda) {
                temp[j + 1] = temp[j];
                j--;
            }
            temp[j + 1] = kunci;
        }

        System.out.println("\nSetelah diurutkan dengan Insertion Sort (Denda terbesar):");
        for (Peminjaman p : temp) {
            p.tampilPeminjaman();
        }
    }

    static void cariBerdasarkanNIM(String nimCari) {
        Peminjaman[] temp = dataPinjam.clone();
        int n = temp.length;

        for (int i = 1; i < n; i++) {
            Peminjaman kunci = temp[i];
            int j = i - 1;
            while (j >= 0 && temp[j].mhs.nim.compareTo(kunci.mhs.nim) > 0) {
                temp[j + 1] = temp[j];
                j--;
            }
            temp[j + 1] = kunci;
        }

        int low = 0;
        int high = n - 1;
        int found = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = temp[mid].mhs.nim.compareTo(nimCari);

            if (cmp == 0) {
                found = mid;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (found == -1) {
            System.out.println("Data tidak ditemukan!");
        } else {
            int start = found;
            int end = found;
            while (start > 0 && temp[start - 1].mhs.nim.equals(nimCari))
                start--;
            while (end < n - 1 && temp[end + 1].mhs.nim.equals(nimCari))
                end++;

            System.out.println("[Binary Search] Data ditemukan:");
            for (int i = start; i <= end; i++) {
                temp[i].tampilPeminjaman();
            }
        }
    }

    static void tampilkanStatistik() {
        int totalDenda = 0;
        int jumlahTerlambat = 0;
        int jumlahTepat = 0;

        for (Peminjaman p : dataPinjam) {
            totalDenda += p.denda;
            if (p.terlambat > 0) {
                jumlahTerlambat++;
            } else {
                jumlahTepat++;
            }
        }

        System.out.println("\n=== STATISTIK PEMINJAMAN ===");
        System.out.println("Total Denda Keseluruhan: Rp " + totalDenda);
        System.out.println("Jumlah Peminjaman Terlambat: " + jumlahTerlambat);
        System.out.println("Jumlah Peminjaman Tepat Waktu: " + jumlahTepat);
    }

    static void tampilkanLaporanMahasiswa() {
        LaporanMahasiswa[] laporan = new LaporanMahasiswa[dataMhs.length];

        for (int i = 0; i < dataMhs.length; i++) {
            laporan[i] = new LaporanMahasiswa(dataMhs[i]);
            laporan[i].hitungLaporan(dataPinjam);
        }

        System.out.println("\n=== LAPORAN MAHASISWA ===");
        for (LaporanMahasiswa lm : laporan) {
            lm.tampilLaporan();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== SISTEM PEMINJAMAN RUANG BACA JTI ===");
            System.out.println("1. Tampilkan Mahasiswa");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Tampilkan Peminjaman");
            System.out.println("4. Urutkan Berdasarkan Denda");
            System.out.println("5. Cari Berdasarkan NIM");
            System.out.println("6. Input Peminjaman");
            System.out.println("7. Tampilkan Statistik");
            System.out.println("8. Tampilkan Laporan Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1:
                    tampilkanMahasiswa();
                    break;
                case 2:
                    tampilkanBuku();
                    break;
                case 3:
                    tampilkanPeminjaman();
                    break;
                case 4:
                    urutkanBerdasarkanDenda();
                    break;
                case 5:
                    System.out.print("Masukkan NIM: ");
                    String nimInput = sc.next();
                    cariBerdasarkanNIM(nimInput);
                    break;
                case 6:
                    inputPeminjaman();
                    break;
                case 7:
                    tampilkanStatistik();
                    break;
                case 8:
                    tampilkanLaporanMahasiswa();
                    break;
                case 0:
                    System.out.println("Keluar dari program. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }
}