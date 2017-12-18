/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naufal.model;

/**
 *
 * @author user
 */
public class OrderList {

    private int idBarang;
    private String productName;
    private String image;
    private double price;
    private int jumlahBeli;
    private String tglPembelian;
    private String pembeli;

    public OrderList() {
    }

    public OrderList(int idBarang, String productName, String image, double price, int jumlahBeli, String tglPembelian, String pembeli) {
        this.idBarang = idBarang;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.jumlahBeli = jumlahBeli;
        this.tglPembelian = tglPembelian;
        this.pembeli = pembeli;
    }


    /**
     * @return the idBarang
     */
    public int getIdBarang() {
        return idBarang;
    }

    /**
     * @param idBarang the idBarang to set
     */
    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    /**
     * @return the jumlahBeli
     */
    public int getJumlahBeli() {
        return jumlahBeli;
    }

    /**
     * @param jumlahBeli the jumlahBeli to set
     */
    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    /**
     * @return the tglPembelian
     */
    public String getTglPembelian() {
        return tglPembelian;
    }

    /**
     * @param tglPembelian the tglPembelian to set
     */
    public void setTglPembelian(String tglPembelian) {
        this.tglPembelian = tglPembelian;
    }

    /**
     * @return the pembeli
     */
    public String getPembeli() {
        return pembeli;
    }

    /**
     * @param pembeli the pembeli to set
     */
    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
