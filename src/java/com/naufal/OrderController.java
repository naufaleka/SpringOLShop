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
                return "redirect:/products";
            } else {
                cartItems = (Map<Integer, OrderList>) session.getAttribute("temp");
                if (cartItems.get(produkId).getIdBarang() == produkId) {
                    int x = (int) session.getAttribute("cart");
                    Map<Integer, OrderList> orderDariSession = (Map<Integer, OrderList>) session.getAttribute("temp");
                    cartItems.replace(produkId, (OrderList) orderDariSession, new OrderList(produkId, (cartItems.get(produkId).getJumlahBeli()+1), new Date().toString(), session.getAttribute("user").toString()));
                    session.setAttribute("cart", x + 1);
                    session.setAttribute("temp", cartItems);
                    return "redirect:/products";
                } else {
                    cartItems.put(produkId, new OrderList(produkId, cartItems.get(produkId).getJumlahBeli() + 1, new Date().toString(), session.getAttribute("user").toString()));
                    session.setAttribute("cart", cartItems.get(produkId).getJumlahBeli() + 1);
                    session.setAttribute("temp" + produkId, cartItems);
                    return "redirect:/products";
                }
            }
        }
        return "Errors";
    }
}
