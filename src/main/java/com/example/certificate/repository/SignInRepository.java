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
    public void registUser(String id,String pass){

    }
}
