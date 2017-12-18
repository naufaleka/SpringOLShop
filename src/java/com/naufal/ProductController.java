/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naufal;

import com.naufal.dao.ProductDao;
import com.naufal.model.Prodcut;
import java.util.List;
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
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductDao dao;

    @RequestMapping()
    public String showAllProduct(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            List<Prodcut> product = dao.findAll();
            for (Prodcut prodcut : product) {
                System.out.println("Product Name " + prodcut.getProductName());
            }
            model.addAttribute("products", product);
            return "product";
        }
        return "Errors";
    }

    @RequestMapping(value = "/{produkId}")
    public String showDetailProduct(HttpSession session, @PathVariable Integer produkId, Model model) {
        if (session.getAttribute("user") != null) {
            Prodcut product = dao.findById(produkId);
            model.addAttribute("produk", product);
            return "DetailProduk";
        } else {
            return "Errors";
        }
    }
}
