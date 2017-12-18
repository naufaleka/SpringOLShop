/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naufal;

import com.naufal.dao.ProductDao;
import com.naufal.model.OrderList;
import com.naufal.model.Prodcut;
import com.naufal.model.ProductModel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ProductDao dao;

    @RequestMapping(value = "/{produkId}")
    public String ProductToCart(HttpSession session, @PathVariable Integer produkId, Model model) {
        if (session.getAttribute("user") != null) {
            Map<Integer, OrderList> cartItems = new HashMap<>();
            if (session.getAttribute("cart").equals(0)) {
                cartItems.put(produkId, new OrderList(produkId, 1, new Date().toString(), session.getAttribute("user").toString()));
                session.setAttribute("cart", 1);
                session.setAttribute("temp", cartItems);
                for (OrderList obj : cartItems.values()) {
                    System.out.println("Masuk Proses Awal" + obj.getJumlahBeli());
                }
                return "redirect:/products";
            } else {
                cartItems = (Map<Integer, OrderList>) session.getAttribute("temp");
                if (cartItems.get(produkId) == null) {
                    System.out.println("Masuk Kalo ID KOSONG");
                    int x = (int) session.getAttribute("cart");
                    Map<Integer, OrderList> ord = (Map<Integer, OrderList>) session.getAttribute("temp");
                    Map<Integer, OrderList> baru = new HashMap<>();
                    baru.put(produkId, new OrderList(produkId, 1, new Date().toString(), session.getAttribute("user").toString()));
                    cartItems.putAll(ord);
                    cartItems.putAll(baru);
                    session.setAttribute("cart", x + 1);
                    session.setAttribute("temp", cartItems);
                    return "redirect:/products";
                } else if (cartItems.get(produkId).getIdBarang() == produkId) {
                    System.out.println("Masuk Kalo IDnya sama");
                    int x = (int) session.getAttribute("cart");
                    Map<Integer, OrderList> ord = (Map<Integer, OrderList>) session.getAttribute("temp");
                    OrderList ordersebelumnya = new OrderList(ord.get(produkId).getIdBarang(), ord.get(produkId).getJumlahBeli(), ord.get(produkId).getTglPembelian(), ord.get(produkId).getPembeli());
                    int beli = 0;
                    for (OrderList obj : cartItems.values()) {
                        beli = obj.getJumlahBeli()+1;
                    }
                    System.out.println("Jumlah Belinya "+beli);
                    cartItems.replace(produkId, ordersebelumnya, new OrderList(produkId, beli, new Date().toString(), session.getAttribute("user").toString()));
                    session.setAttribute("cart", x + 1);
                    session.setAttribute("temp", cartItems);
                    return "redirect:/products";
                }
            }
        }
        return "Errors";
    }

    @RequestMapping(value = "checkOut")
    public String checkOut(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            Map<Integer, OrderList> cartItems = (Map<Integer, OrderList>) session.getAttribute("temp");
            System.out.println("Cart Items" + cartItems.values());
            for (OrderList obj : cartItems.values()) {
                System.out.println("ID Barang : " + obj.getIdBarang());
                System.out.println("Jumlah Beli : " + obj.getJumlahBeli());
                System.out.println("Tanggal Beli : " + obj.getTglPembelian());
                System.out.println("Nama Pembeli : " + obj.getPembeli());
            }
        }
        return "redirect:/products";
    }
}
