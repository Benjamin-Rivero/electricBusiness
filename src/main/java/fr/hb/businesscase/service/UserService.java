package fr.hb.businesscase.service;

import fr.hb.businesscase.dto.UserRegistrationDTO;
import fr.hb.businesscase.entity.Address;
import fr.hb.businesscase.entity.User;
import fr.hb.businesscase.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;

    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        Address address = addressService.createAddressFromUserRegistration(userRegistrationDTO.getAddressDto());
        address.setOwner(user);
        user.getAddresses().add(address);
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setPhone(userRegistrationDTO.getPhone());
        user.setActivationToken(UUID.randomUUID().toString());
        user.setBirthDate(userRegistrationDTO.getBirthDate());
        return userRepository.saveAndFlush(user);
    }

}
