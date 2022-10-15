package Alkemy.Disney.services;

import Alkemy.Disney.models.User;

public interface UserServices {

    User getUserByEmail(String email);

    void saveUser(User user);

}
