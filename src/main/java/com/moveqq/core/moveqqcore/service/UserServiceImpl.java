package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.UserEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.User;
import com.moveqq.core.moveqqcore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserEntity createUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        return userRepository.saveUserWithEncryption(userEntity);
    }

    @Override
    @Transactional
    public String checkUserCredentials(User user) throws Exception {
      return userRepository.getUserEntityByLoginWithAuthentication(user.getLogin(), user.getPassword())
                           .getLogin();
    }
}
