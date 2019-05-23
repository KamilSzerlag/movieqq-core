package com.moveqq.core.moveqqcore.repository;

import com.moveqq.core.moveqqcore.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticationRepository {

   UserEntity saveUserWithEncryption(UserEntity userEntity);

   UserEntity getUserEntityByLoginWithAuthentication(String login, String password) throws Exception;
}
