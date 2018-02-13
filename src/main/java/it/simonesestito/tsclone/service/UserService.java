package it.simonesestito.tsclone.service;

import it.simonesestito.tsclone.model.db.User;
import it.simonesestito.tsclone.model.dto.LoginResult;
import it.simonesestito.tsclone.model.dto.LoginUser;

public interface UserService {
    boolean saveUser(User user);
    User findByUsername(String username);
    LoginResult generateToken(LoginUser loginUser);
}
