package com.scm.services;

import java.util.*;

import com.scm.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String userId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
    

    //add more methods have related user services[logic]
}
