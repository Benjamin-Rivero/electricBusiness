package fr.hb.businesscase.service;

//import fr.hb.businesscase.configuration.PasswordEncoderConfig;
import fr.hb.businesscase.dto.UserRegistrationDTO;
import fr.hb.businesscase.entity.Address;
import fr.hb.businesscase.entity.User;
import fr.hb.businesscase.entity.UserAddress;
import fr.hb.businesscase.repository.UserRepository;
import fr.hb.businesscase.security.PasswordEncoderConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private PasswordEncoderConfig encoder;

    private final EmailService emailService;

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final UserAddressService userAddressService;

    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        Address address = addressService.createAddressFromUserRegistration(userRegistrationDTO.getAddressDto());

        UserAddress userAddress = new UserAddress();
        userAddress.setUser(user);
        userAddress.setAddress(address);
        userAddress.setBilling(userRegistrationDTO.getAddressDto().getUserAddressDto().isBilling());

        user.getAddresses().add(address);
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setPassword(encoder.passwordEncoder().encode(userRegistrationDTO.getPassword()));
        user.setPassword(userRegistrationDTO.getPassword());
        user.setPhone(userRegistrationDTO.getPhone());
        user.setActivationToken(UUID.randomUUID().toString());
        user.setActivationTokenSentAt(LocalDateTime.now());
        user.setBirthDate(userRegistrationDTO.getBirthDate());
        user = userRepository.saveAndFlush(user);
        userAddress = userAddressService.createUserAddressFromRegistration(userAddress);
        user.getUserAddresses().add(userAddress);
        try {
            emailService.sendVerificationEmail(user);
        } catch (MessagingException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return userRepository.saveAndFlush(user);
    }

    public void verifyAccount(String token) {
        try {
            User user = userRepository.findByActivationToken(token).orElseThrow(EntityNotFoundException::new);
            user.setActivationToken(null);
            userRepository.saveAndFlush(user);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }

    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public User findBySlug(String slug){
        return userRepository.findBySlug(slug).orElseThrow(EntityNotFoundException::new);
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = optionalUser.get();

        if(!user.isEnabled()) return null;

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                userGrantedAuthority(user.getRoles())
        );
    }

    private List<GrantedAuthority> userGrantedAuthority(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = Collections.singletonList(role);
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (r.contains("ADMIN")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        });
        return authorities;
    }
}
