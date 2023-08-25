package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarFud {
    private final ArrayList<MenuItem> daftarMenu = new ArrayList<>();
    private final ArrayList<OrderItem> pesanan = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void halamanUtama(){

        System.out.println(printGaris(40, "="));
        System.out.println("\t\tSelamat Datang Di BinarFud");
        System.out.println(printGaris(40, "="));

        // Inisialisasi data menu
        initDataMenu();

        int pilihan;
        do {
            clearScreen();
            daftarMenu();
            System.out.print("=> ");
            pilihan = getInputInt();

            switch (pilihan) {
                case 1: case 2: case 3: case 4: case 5:
                    pesanMakanan(pilihan-1);
                    break;
                case 99:
                    if(pesanan.isEmpty()) {
                        System.out.println("Anda belum memesan apapun.");
                    } else {
                        displayTotalBayar();
                    }
                    break;
                case 0:
                    System.out.println("Terima kasih. Selamat tinggal!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
//            pause();
        } while (pilihan != 0 && pilihan != 99);
    }

    private void initDataMenu() {
        daftarMenu.add(new MenuItem(1, "Nasi Goreng", 15000.0));
        daftarMenu.add(new MenuItem(2, "Mie Goreng", 13000.0));
        daftarMenu.add(new MenuItem(3, "Nasi + Ayam", 18000.0));
        daftarMenu.add(new MenuItem(4, "Es Teh Manis", 3000.0));
        daftarMenu.add(new MenuItem(5, "Es Jeruk", 5000.0));
    }

    private void daftarMenu() {
        System.out.println("Silahkan Pilih Menu: ");
        for (MenuItem menu : daftarMenu) {
            System.out.println(menu.toString());
        }
        System.out.println("99. Pesan Dan Bayar");
        System.out.println("0. Keluar Aplikasi");
    }

    private void pesanMakanan(int nomorMakanan) {
        System.out.println(printGaris(40, "="));
        System.out.println("\t\tBerapa Pesanan Anda");
        System.out.println(printGaris(40, "="));
        System.out.println(daftarMenu.get(nomorMakanan).getNamaMakanan());

        System.out.print("Masukkan jumlah pesanan: ");
        int jumlahPesanan = getInputInt();

        if(jumlahPesanan > 0) {
            MenuItem menu = daftarMenu.get(nomorMakanan);
            OrderItem orderItem = new OrderItem(menu, jumlahPesanan);
            pesanan.add(orderItem);
        }
    }

    private void displayTotalBayar() {
        System.out.println(printGaris(40, "="));
        System.out.println("\t\tKonfirmasi & Pembayaran");
        System.out.println(printGaris(40, "="));
        double totalHarga = 0;
        int totalQty = 0;
        for (OrderItem orderItem : pesanan) {
            totalHarga += orderItem.getTotalHarga();
            totalQty += orderItem.getQty();
            System.out.println(orderItem);
        }
        System.out.println(printGaris(40, "-") + "+");
        System.out.println("Total\t\t\t " + totalQty + "\t\t " + totalHarga);

        System.out.println("1. Konfirmasi Dan Bayar");
        System.out.println("2. Kembali Ke Menu Utama");
        System.out.println("3. Keluar Aplkasi");
        System.out.print("=> ");
        int pilihan = getInputInt();
        do {
            switch (pilihan){
                case 1:
                    printStruk();
                    pesanan.clear();
                    break;
                case 2:
                    halamanUtama();
                    break;
                case 3:
                    System.out.println("Terima kasih. Selamat tinggal!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while(pilihan != 1 && pilihan != 2 && pilihan != 3);
    }

    private void printStruk() {
        try (PrintWriter writer = new PrintWriter("struk.txt")) {
            writer.println(printGaris(40, "="));
            writer.println("\t\t\tBinarFud");
            writer.println(printGaris(40, "="));
            writer.println("Terima Kasih Sudah Memesan Di BinarFud");
            writer.println("Dibawah Ini Adalah Pesanan Anda");
            double totalHarga = 0;
            int totalQty = 0;
            for (OrderItem orderItem : pesanan) {
                totalHarga += orderItem.getTotalHarga();
                totalQty += orderItem.getQty();
                writer.println(orderItem);
            }
            writer.println(printGaris(40, "-") + "+");
            writer.println("Total\t\t\t " + totalQty + "\t\t " + totalHarga);
            writer.println("Pembayaran : BinarCash");
            writer.println(printGaris(40, "="));
            writer.println("Simpan Struk Ini Sebagai Bukti Pembayaran Anda");
            writer.println(printGaris(40, "="));
            System.out.println("Struk telah disimpan dalam file 'struk.txt'");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis file.");
            e.printStackTrace();
        }
    }

    private void clearScreen() {
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

//    private void pause() {
//        System.out.println("Tekan Enter untuk melanjutkan...");
//        try {
//            System.in.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private  String printGaris(int panjang, String karakter) {
        StringBuilder garis = new StringBuilder();
        for (int i = 0; i < panjang; i++) {
            garis.append(karakter);
        }
        return garis.toString();
    }

    private int getInputInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Masukkan angka yang valid: ");
            scanner.next(); // Membuang input yang tidak valid
        }
        return scanner.nextInt();
    }

}
