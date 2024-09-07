package fr.hb.businesscase.service;

//import fr.hb.businesscase.configuration.PasswordEncoderConfig;
import fr.hb.businesscase.dto.UserRegistrationDTO;
import fr.hb.businesscase.entity.Address;
import fr.hb.businesscase.entity.User;
import fr.hb.businesscase.entity.UserAddress;
import fr.hb.businesscase.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

//    private PasswordEncoderConfig encoder;

    private Environment env;

    private JavaMailSender mailSender;

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
//        user.setPassword(encoder.passwordEncoder().encode(userRegistrationDTO.getPassword()));
        user.setPassword(userRegistrationDTO.getPassword());
        user.setPhone(userRegistrationDTO.getPhone());
        user.setActivationToken(UUID.randomUUID().toString());
        user.setBirthDate(userRegistrationDTO.getBirthDate());
        user = userRepository.saveAndFlush(user);
        userAddress = userAddressService.createUserAddressFromRegistration(userAddress);
        user.getUserAddresses().add(userAddress);
        try {
            sendVerificationEmail(user);
        } catch (MessagingException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return userRepository.saveAndFlush(user);
    }

    public void sendVerificationEmail(User user) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = env.getProperty("spring.mail.username");
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Electricity Business.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = "http://localhost:8080/user" + "/verify?token=" + user.getActivationToken();

        content = content.replace("[[URL]]", verifyURL);
        System.out.println(content);

        helper.setTo(toAddress);
        helper.setFrom(fromAddress);
        helper.setSubject(subject);
        helper.setText(content,true);


        mailSender.send(message);
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
}
