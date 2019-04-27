package com.moveqq.core.moveqqcore.user;

import com.moveqq.core.moveqqcore.entity.UserEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.User;
import com.moveqq.core.moveqqcore.repository.UserRepository;
import com.moveqq.core.moveqqcore.service.UserService;
import com.moveqq.core.moveqqcore.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private PasswordEncoder passwordEncoder;


    private static final String LOGIN = "KungFuPanda123";
    private static final String PASSWORD = "LovePandzia";
    private User user;
    private UserEntity userEntity;
    private UserEntity userEntityWithEncryptedPw;
    private String encryptedPassword;


    @Before
    public void setUp() throws Exception {
        this.userService = new UserServiceImpl(userRepository);
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.user = new User();
        this.user.setLogin(LOGIN);
        this.user.setPassword(PASSWORD);
        this.userEntity = new UserEntity();
        this.userEntity.setLogin(user.getLogin());
        this.userEntity.setPassword(user.getPassword());
        this.userEntityWithEncryptedPw = new UserEntity();
        this.userEntityWithEncryptedPw.setLogin(user.getLogin());
        String encryptedPassword = passwordEncoder.encode(userEntity.getPassword());
        this.userEntityWithEncryptedPw.setPassword(encryptedPassword);
    }


    @Test
    public void should_Return_UserEntity_With_Encrypted_Password() {
        when(userRepository.saveUserWithEncryption(userEntity)).thenReturn(userEntityWithEncryptedPw);
        UserEntity userAsserted = userService.createUser(user);
        assertThat(userAsserted).isEqualTo(userEntityWithEncryptedPw);
    }

    @Test
    public void should_Return_Login_If_PasswordMatch() throws Exception {
        when(userRepository.getUserEntityByLoginWithAuthentication(LOGIN,PASSWORD)).thenReturn(userEntityWithEncryptedPw);
        String userLogin = userService.checkUserCredentials(user);
        assertThat(userLogin).isEqualTo(LOGIN);
    }


}