package it.simonesestito.tsclone.service.impl;

import it.simonesestito.tsclone.jwt.JwtUtils;
import it.simonesestito.tsclone.model.db.User;
import it.simonesestito.tsclone.model.dto.LoginResult;
import it.simonesestito.tsclone.model.dto.LoginUser;
import it.simonesestito.tsclone.repository.UserRepository;
import it.simonesestito.tsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.signing-key}")
    private String signingKey;

    @Value("${jwt.expire-time}")
    private long expireTime;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean saveUser(User user) {
        User oldUser = userRepository.findByUsername(user.getUsername());
        if (oldUser != null)
            return false; // Return false if there is an error in saving process

        // Encrypt password
        String password = user.getPassword();
        password = passwordEncoder.encode(password);
        user.setPassword(password);

        userRepository.save(user);
        return true;
    }

    @Override
    public LoginResult generateToken(LoginUser loginUser) {
        LoginResult result = new LoginResult();
        String username = loginUser.getUsername();
        result.setUsername(username);
        User user = findByUsername(username);
        if (user == null) return result;
        boolean passwordCheck =
                passwordEncoder.matches(loginUser.getPassword(), user.getPassword());
        if (passwordCheck){
            long expDate = System.currentTimeMillis() + expireTime*1000;
            String token = JwtUtils.getToken(username, expDate, signingKey.getBytes());
            result.setToken(token);
            result.setExpireDate(expDate);
        }
        return result;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
