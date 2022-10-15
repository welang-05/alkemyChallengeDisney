package Alkemy.Disney.services;

import Alkemy.Disney.models.Client;

public interface ClientServices {

    Client getUserByEmail(String email);

    void saveUser(Client client);

}
