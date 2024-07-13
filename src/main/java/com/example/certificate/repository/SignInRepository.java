package com.example.certificate.repository;

import com.example.certificate.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SignInRepository {
    private final EntityManager em;
    public List<User> checkId(String id){
        return em.createQuery("SELECT m FROM User m WHERE m.id =:id ", User.class)
                .setParameter("id", id)
                .getResultList();
    }
    public List<User> logIn(String id,String password){
        return em.createQuery("SELECT m FROM User m WHERE m.id =:id and m.password =:password", User.class)
                .setParameter("id", id)
                .setParameter("password", password)
                .getResultList();
    }
    public void registUser(User user){
        em.persist(user);
    }
}
