package com.moveqq.core.moveqqcore.repository;

import com.moveqq.core.moveqqcore.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class UserRepositoryImpl implements UserAuthenticationRepository{

    private EntityManager em;
    private PasswordEncoder passwordEncoder;

    public UserRepositoryImpl(EntityManager em, PasswordEncoder passwordEncoder) {
        this.em = em;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserEntity saveUserWithEncryption(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        em.persist(userEntity);
        em.flush();
        return userEntity;
    }

    @Override
    @Transactional
    public UserEntity getUserEntityByLoginWithAuthentication(String login, String password) throws Exception{
        UserEntity user = em.createQuery("select login, password from UserEntity where login=:login", UserEntity.class)
                            .setParameter("login",login).getSingleResult();
        boolean correctPassword = passwordEncoder.matches(password, user.getPassword());
        if (!correctPassword)
            throw new Exception("Incorrect Login or Password");
        return user;
    }

}
