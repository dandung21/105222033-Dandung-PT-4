class Mobil {
    private String nomorPlat, merek;
    private double hargaSewaPerHari;
    private boolean tersedia;

    public Mobil(String nomorPlat, String merek, double hargaSewaPerHari) {
        this.nomorPlat = nomorPlat;
        this.merek = merek;
        this.hargaSewaPerHari = hargaSewaPerHari;
        this.tersedia = true;
    }

    public boolean isTersedia() { return tersedia; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }
    public double getHargaSewaPerHari() { return hargaSewaPerHari; }
    public void tampilkanInfo() {
        System.out.println(nomorPlat + " - " + merek + " - " + Utility.formatMataUang(hargaSewaPerHari) + " - " + (tersedia ? "Tersedia" : "Tidak"));
    }
}

class Pelanggan {
    private String nama, nomorKTP, nomorHP;
    public Pelanggan(String nama, String nomorKTP, String nomorHP) {
        this.nama = nama; this.nomorKTP = nomorKTP; this.nomorHP = nomorHP;
    }
    public void tampilkanInfo() {
        System.out.println(nama + " - " + nomorKTP + " - " + nomorHP);
    }
}

class Utility {
    public static double hitungDiskon(double totalBiaya, int lamaSewa) {
        return (lamaSewa > 5) ? totalBiaya * 0.9 : totalBiaya;
    }
    public static String formatMataUang(double angka) {
        return String.format("Rp %, .2f", angka);
    }
}

class Sewa {
    private Pelanggan pelanggan;
    private Mobil mobil;
    private int lamaSewa;

    public Sewa(Pelanggan pelanggan, Mobil mobil, int lamaSewa) {
        this.pelanggan = pelanggan;
        this.mobil = mobil;
        this.lamaSewa = lamaSewa;
    }

    public void prosesSewa() {
        if (mobil.isTersedia()) {
            mobil.setTersedia(false);
            cetakStruk();
        } else {
            System.out.println("Transaksi gagal! Mobil tidak tersedia.");
        }
    }

    public void cetakStruk() {
        System.out.println("=== STRUK ===");
        pelanggan.tampilkanInfo();
        mobil.tampilkanInfo();
        System.out.println("Lama Sewa: " + lamaSewa + " hari");
        System.out.println("Total: " + Utility.formatMataUang(Utility.hitungDiskon(mobil.getHargaSewaPerHari() * lamaSewa, lamaSewa)));
    }
}

public class Main {
    public static void main(String[] args) {
        Mobil mobil1 = new Mobil("B 1234 XY", "Avanza", 500000);
        Pelanggan pelanggan1 = new Pelanggan("Andi", "1234567890", "08123456789");
        System.out.println("Daftar Mobil:");
        mobil1.tampilkanInfo();
        System.out.println("\nPelanggan menyewa...");
        new Sewa(pelanggan1, mobil1, 6).prosesSewa();
    }
}
