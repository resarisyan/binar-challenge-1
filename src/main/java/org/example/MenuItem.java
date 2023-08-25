package org.example;

public class MenuItem {
    private int key;
    private String namaMakanan;
    private double harga;

    public MenuItem(int key, String namaMakanan, double harga) {
        this.key = key;
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return String.format("%d. %s\t | %.2f", key, namaMakanan, harga);
    }
}

