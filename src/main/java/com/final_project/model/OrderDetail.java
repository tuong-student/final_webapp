package com.final_project.model;

public class OrderDetail {
    private double total;
    private double shipping;
    private double subtotal;
    private double tax;

    public OrderDetail(double total, double shipping, double subtotal, double tax) {
        super();
        this.total = total;
        this.shipping = shipping;
        this.subtotal = subtotal;
        this.tax = tax;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTotal() {
        return String.format("%.2f", this.total);
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public String getShipping() {
        return String.format("%.2f", shipping);
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getSubtotal() {
        return String.format("%.2f", subtotal);
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getTax() {
        return String.format("%.2f", tax);
    }
}
