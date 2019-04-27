package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.UserEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.User;
import com.moveqq.core.moveqqcore.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserEntity createUser(User user) {
        UserEntity userEntity = new UserEntity();
        user.setLogin(user.getLogin());
        user.setPassword(user.getPassword());
        return userRepository.saveUserWithEncryption(userEntity);
    }

    @Override
    @Transactional
    public String checkUserCredentials(User user) throws Exception {
      return userRepository.getUserEntityByLoginWithAuthentication(user.getLogin(), user.getPassword())
                           .getLogin();
    }
}
