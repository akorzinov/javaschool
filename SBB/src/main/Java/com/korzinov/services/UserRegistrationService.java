package com.korzinov.services;

import com.korzinov.models.UserModel;

public interface UserRegistrationService {

    void createUser(UserModel user);

    boolean validateUser(UserModel user, String confirmPassword);
}
