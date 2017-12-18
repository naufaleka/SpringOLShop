/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naufal;

import com.naufal.model.Login;
import com.naufal.dao.UserDao;
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
@RequestMapping("/welcome")
public class LoginController {

    @Autowired
    UserDao dao;

    @RequestMapping()
    public String welcome(Model model) {
        Login loginBean = new Login();// SET DIAWAL JANGAN LUPA SAYANGGG
        model.addAttribute("loginBean", loginBean); // SET DIAWAL JANGAN LUPA SAYANGGG
        model.addAttribute("msg", "Selamat datang di Indocyber Online Shop");
        return "welcome";
    }

    @RequestMapping(value = "/product")
    public String checkLogin(HttpSession session, @ModelAttribute("loginBean") Login login, Model model) {
        User users = dao.findByEmail(login.getEmail());
        if (users.getEmail() == null) {
            model.addAttribute("msg", "Email Tidak Dikenali");
            return "welcome";
        }
        String encryptedPassword = PasswordDigest.createEncryptedPassword(login.getPassword());
//        System.out.println("password : "+encryptedPassword+ " = "+users.getPassword());
        if (!encryptedPassword.equals(users.getPassword())) {
            model.addAttribute("msg", "Password salah");
            return "welcome";
        }
        session.removeAttribute("data");
        session.setAttribute("cart", 0);
        session.setAttribute("user", users.getNama());
        return "redirect:/products";
    }

    @RequestMapping(value = "logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        return "redirect:../welcome";
    }
}
