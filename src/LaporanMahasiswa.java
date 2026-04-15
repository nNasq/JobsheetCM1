public class LaporanMahasiswa {
    Mahasiswa mhs;
    int totalPinjam;
    int totalDenda;
    int totalTerlambat;

    LaporanMahasiswa(Mahasiswa mhs) {
        this.mhs = mhs;
        this.totalPinjam = 0;
        this.totalDenda = 0;
        this.totalTerlambat = 0;
    }

    void hitungLaporan(Peminjaman[] pinjam) {
        for (Peminjaman p : pinjam) {
            if (p.mhs.nim.equals(this.mhs.nim)) {
                totalPinjam++;
                totalDenda += p.denda;
                if (p.terlambat > 0) {
                    totalTerlambat++;
                }
            }
        }
    }

    void tampilLaporan() {
        System.out.println("NIM: " + mhs.nim +
                " | Nama: " + mhs.nama +
                " | Total Pinjam: " + totalPinjam +
                " | Total Denda: Rp " + totalDenda +
                " | Terlambat: " + totalTerlambat + "x");
    }
}