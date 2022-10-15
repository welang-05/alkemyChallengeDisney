package Alkemy.Disney.services.implementations;

import Alkemy.Disney.models.Client;
import Alkemy.Disney.repositories.ClientRepository;
import Alkemy.Disney.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServicesImp implements ClientServices {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client getUserByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveUser(Client client){
        clientRepository.save(client);
    }

}
