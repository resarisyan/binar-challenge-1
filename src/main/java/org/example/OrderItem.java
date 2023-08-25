package org.example;

class OrderItem {
    private final MenuItem menuItem;
    private final int qty;

    public OrderItem(MenuItem menuItem, int qty) {
        this.menuItem = menuItem;
        this.qty = qty;
    }

    public double getTotalHarga() {
        return menuItem.getHarga() * qty;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return String.format("%s\t\t %d\t\t %.2f", menuItem.getNamaMakanan(), qty, getTotalHarga());
    }
}