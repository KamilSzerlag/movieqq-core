package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.UserEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.User;

public interface UserService {

    UserEntity createUser(User user);

    String checkUserCredentials(User user) throws Exception;

}
