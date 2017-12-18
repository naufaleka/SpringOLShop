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
import java.util.List;
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
                List<Prodcut> pro = dao.findByIds(produkId);
                for (Prodcut p : pro) {
                    cartItems.put(produkId, new OrderList(p.getId(), p.getProductName(), p.getPicture(), p.getProductPrice(), 1, new Date().toString(), session.getAttribute("user").toString()));
                }
                session.setAttribute("cart", 1);
                session.setAttribute("temp", cartItems);
                for (OrderList obj : cartItems.values()) {
                    System.out.println("Masuk Proses Awal : " + obj.getJumlahBeli());
                }
                return "redirect:/products";
            } else {
                cartItems = (Map<Integer, OrderList>) session.getAttribute("temp");
                if (cartItems.get(produkId) == null) {
                    System.out.println("Masuk Kalo ID KOSONG");
                    int x = (int) session.getAttribute("cart");
                    Map<Integer, OrderList> baru = new HashMap<>();
                    List<Prodcut> pro = dao.findByIds(produkId);
                    for (Prodcut p : pro) {
                        baru.put(produkId, new OrderList(p.getId(), p.getProductName(), p.getPicture(), p.getProductPrice(), 1, new Date().toString(), session.getAttribute("user").toString()));
                    }
                    cartItems.putAll(baru); // untuk menggabung dari isi data sebelumnya
                    session.setAttribute("cart", x + 1);
                    session.setAttribute("temp", cartItems);
                    return "redirect:/products";
                } else if (cartItems.get(produkId).getIdBarang() == produkId) {
                    System.out.println("Masuk Kalo IDnya sama");
                    int x = (int) session.getAttribute("cart");
                    int beli = 0;
                    for (OrderList obj : cartItems.values()) {
                        if (obj.getIdBarang() == produkId) {
                            beli = obj.getJumlahBeli() + 1;
                        }
                    }
                    List<Prodcut> pro = dao.findByIds(produkId);
                    for (Prodcut p : pro) {
                        cartItems.put(produkId, new OrderList(p.getId(), p.getProductName(), p.getPicture(), p.getProductPrice(), beli, new Date().toString(), session.getAttribute("user").toString()));
                    }
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
            Map<Integer, OrderList> cartItems = new HashMap<>();
            cartItems = (Map<Integer, OrderList>) session.getAttribute("temp");
            model.addAttribute("produk", cartItems.values());
            for (OrderList obj : cartItems.values()) {
                System.out.println("ID Barang : " + obj.getIdBarang());
                System.out.println("Jumlah Beli : " + obj.getJumlahBeli());
                System.out.println("Tanggal Beli : " + obj.getTglPembelian());
                System.out.println("Nama Pembeli : " + obj.getPembeli());
            }
        }
        return "checkOut";
    }
}
