/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naufal;

import com.naufal.dao.UserDao;
import com.naufal.model.Registrasi;
import com.naufal.model.User;
import com.naufal.utils.PasswordDigest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserDao dao;

    @RequestMapping()
    public String goToRegister(Model model) {
        Registrasi rgs = new Registrasi();
        model.addAttribute("registerBean", rgs);
        return "registrasi";
    }

    @RequestMapping(value = "/save")
    public String saveDataUser(HttpSession session, @ModelAttribute("registerBean") Registrasi reg, Model model) {
        if (reg.getPassword().equals(reg.getRePassword())) {
            User users = new User();
            users.setNama(reg.getNama());
            users.setEmail(reg.getEmail());
            users.setPassword(PasswordDigest.createEncryptedPassword(reg.getPassword()));
            users.setJenisKelamin(reg.getJenisKelamin());
            users.setTanggalLahir(reg.getTanggalLahir());
            dao.saveUser(users);
            session.setAttribute("data", "Selamat " + reg.getNama() + " anda telah berhasil Registrasi");
            return "redirect:../welcome";
        }
        model.addAttribute("data", "Mohon Periksa Kembali Password Anda...");
        return "registrasi";
    }
}
