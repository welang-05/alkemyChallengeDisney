package Alkemy.Disney.services.implementations;

import Alkemy.Disney.models.User;
import Alkemy.Disney.repositories.UserRepository;
import Alkemy.Disney.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImp implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

}
