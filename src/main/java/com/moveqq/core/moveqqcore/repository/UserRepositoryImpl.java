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
        return userEntity;
    }

    @Override
    @Transactional
    public UserEntity getUserEntityByLoginWithAuthentication(String login, String password) {
        UserEntity user = em.createQuery("select login, password from users where login = :login", UserEntity.class)
                            .setParameter("login",login).getSingleResult();
        passwordEncoder.matches(password, user.getPassword());
        return user;
    }

}
