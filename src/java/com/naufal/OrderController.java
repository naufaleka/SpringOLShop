/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naufal;

import com.naufal.dao.ProductDao;
import com.naufal.model.OrderList;
import com.naufal.model.Prodcut;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//            for (OrderList obj : cartItems.values()) {
//                System.out.println("ID Barang : " + obj.getIdBarang());
//                System.out.println("Jumlah Beli : " + obj.getJumlahBeli());
//                System.out.println("Tanggal Beli : " + obj.getTglPembelian());
//                System.out.println("Nama Pembeli : " + obj.getPembeli());
//            }
        }
        return "checkOut";
    }

    @RequestMapping(value = "/delete/{idProduk}")
    public String Delete(HttpSession session, @PathVariable Integer idProduk, Model model) {
        String link = "";
        Map<Integer, OrderList> list = new HashMap<>();
        list = (Map<Integer, OrderList>) session.getAttribute("temp");
        int beli = 0;
        int cart = 0;
        if (list.values() != null) {
            for (OrderList obj : list.values()) {
                if (obj.getIdBarang() == idProduk) {
                    if (obj.getJumlahBeli() > 1) {
                        if (list.get(idProduk).getJumlahBeli() == 1) {
                            list.remove(idProduk);
                            cart = (int) session.getAttribute("cart");
                            cart--;
                            session.setAttribute("cart", cart);
                            link = "redirect:/order/checkOut";
                        } else {
                            beli = list.get(idProduk).getJumlahBeli() - 1;
                            cart = (int) session.getAttribute("cart");
                            cart--;
                            session.setAttribute("cart", cart);
                            list.replace(idProduk, new OrderList(obj.getIdBarang(), obj.getProductName(), obj.getImage(), obj.getPrice(), beli, obj.getTglPembelian(), obj.getPembeli()));
                            link = "redirect:/order/checkOut";
                        }
                    } else {
                        cart = (int) session.getAttribute("cart");
                        cart--;
                        session.setAttribute("cart", cart);
                        list.remove(idProduk);
                        link = "redirect:/products";
                    }
                }
            }
        } else {
            return "redirect:/order/checkOut";
        }
        session.setAttribute("temp", list);
        return link;
    }

    @RequestMapping(value = "/bayar")
    public String Bayar(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            session.setAttribute("cart", 0);
            session.removeAttribute("temp");
            session.setAttribute("stlBayar", "Terima Kasih Telah Berbelanja Di Toko Kami...");
            return "redirect:../products";
        } else {
            return "Errors";
        }
    }
}
