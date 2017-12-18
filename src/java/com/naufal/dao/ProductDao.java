/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naufal.dao;

import com.naufal.model.Prodcut;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Repository
@Transactional
public class ProductDao {

    static final Logger logger = Logger.getLogger(ProductDao.class.getName());

    @PersistenceUnit
    EntityManagerFactory emf;
    
    private EntityManager em;
    
    public List<Prodcut> findAll(){
        em = emf.createEntityManager();
        List<Prodcut> products;
        products = em.createNamedQuery("Product.findAll").getResultList();
        return products;
    }
    
    public Prodcut findById(Integer prodId){
        return em.find(Prodcut.class, prodId);
    }
    
    public List<Prodcut> findByIds(Integer pro){
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("Product.findById");
        query.setParameter("productID", pro);
        List<Prodcut> list = query.getResultList();
        return list;
    }
}
