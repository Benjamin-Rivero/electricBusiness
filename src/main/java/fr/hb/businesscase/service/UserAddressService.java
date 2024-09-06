package fr.hb.businesscase.service;

import fr.hb.businesscase.entity.UserAddress;
import fr.hb.businesscase.repository.UserAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;

    public UserAddress createUserAddressFromRegistration(UserAddress userAddress) {
        return userAddressRepository.saveAndFlush(userAddress);
    }

}
