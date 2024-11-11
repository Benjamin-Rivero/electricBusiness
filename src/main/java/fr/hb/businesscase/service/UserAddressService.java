package fr.hb.businesscase.service;

import fr.hb.businesscase.entity.User;
import fr.hb.businesscase.entity.UserAddress;
import fr.hb.businesscase.repository.UserAddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;

    public UserAddress createUserAddressFromRegistration(UserAddress userAddress) {
        return userAddressRepository.saveAndFlush(userAddress);
    }

    public List<UserAddress> findByUser(User user) {
        return userAddressRepository.findByUser(user);
    }

    public UserAddress findById(Long userAddressId) {
        return userAddressRepository.findById(userAddressId).orElseThrow(EntityNotFoundException::new);
    }
}
