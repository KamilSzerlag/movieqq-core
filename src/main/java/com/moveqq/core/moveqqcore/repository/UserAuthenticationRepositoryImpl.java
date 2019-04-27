package com.moveqq.core.moveqqcore.repository;

import com.moveqq.core.moveqqcore.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class UserAuthenticationRepositoryImpl implements UserAuthenticationRepository{

    private EntityManager em;
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(UserAuthenticationRepositoryImpl.class);

    public UserAuthenticationRepositoryImpl(EntityManager em, PasswordEncoder passwordEncoder) {
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

    /**
     * Method created only
     * for testing BCryption
     * in the futer will be
     * replaced by JWT authorization
     *
     *
     * @param login
     * @param password
     * @return
     * @throws Exception
     */

    @Override
    @Transactional
    public UserEntity getUserEntityByLoginWithAuthentication(String login, String password) throws Exception{
        try {
            UserEntity user = em.createQuery("select e from UserEntity e where e.login=:login", UserEntity.class)
                    .setParameter("login",login)
                    .getSingleResult();

            boolean correctPassword = passwordEncoder.matches(password, user.getPassword());
            if (!correctPassword)
                throw new Exception("Incorrect Login or Password");
            return user;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
