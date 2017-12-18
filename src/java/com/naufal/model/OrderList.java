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
    private int jumlahBeli;
    private String tglPembelian;
    private String pembeli;

    public OrderList() {
    }

    public OrderList(int idBarang, int jumlahBeli, String tglPembelian, String pembeli) {
        this.idBarang = idBarang;
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

    
}
