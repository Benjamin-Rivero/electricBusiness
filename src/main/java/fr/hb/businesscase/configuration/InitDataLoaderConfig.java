package fr.hb.businesscase.configuration;

import com.github.javafaker.Faker;
import fr.hb.businesscase.entity.*;
import fr.hb.businesscase.repository.*;
import fr.hb.businesscase.service.AddressService;
import fr.hb.businesscase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;

    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final PowerRepository powerRepository;
    private final UserAddressRepository userAddressRepository;
    private final ChargingStationRepository chargingStationRepository;

    @Override
    public void run(String... args){
        createUsers();
        userRepository.flush();
        createAddressForUser();
        addressRepository.flush();
        createUserAddresses();
        userAddressRepository.flush();
        createStationLocalisations();
        addressRepository.flush();
        createPowers();
        powerRepository.flush();
        createStations();
        chargingStationRepository.flush();
    }

    private void createAddressForUser(){
        Faker faker = new Faker(Locale.FRANCE);
        for(Long i = 1L;i<=50L ; i++){
            if(!addressRepository.existsById(i)) {
                Address address = new Address();
                address.setStreetNumber(faker.address().streetAddressNumber());
                address.setStreet(faker.address().streetAddress());
                address.setCity(faker.address().city());
                address.setZipCode(faker.address().zipCode());
                addressRepository.save(address);
            }
        }
    }

    private void createUsers(){
        Faker faker = new Faker(Locale.of("fr"));

        for(int i = 0 ; i<50;i++){
            if(userRepository.countBy()<50) {
                User user = new User();
                if (i < 5) user.setRole("ADMIN");
                user.setFirstName(faker.name().firstName());
                user.setLastName(faker.name().lastName());
                user.setEmail(faker.internet().emailAddress());
                user.setPassword(faker.internet().password());
                user.setBirthDate(new java.sql.Date(faker.date().birthday(18, 65).getTime()).toLocalDate());
                user.setActivationToken(null);
                user.setPhone(faker.phoneNumber().cellPhone());
                userRepository.save(user);
            }
        }
    }

    private void createUserAddresses(){
        Faker faker = new Faker(Locale.of("fr"));
        for(Long i = 1L ; i<=50L;i++){
            if(!userAddressRepository.existsById(i)) {
                UserAddress userAddress = new UserAddress();
                Address address = addressRepository.findById((long) Math.ceil(Math.random() * 50)).get();
                User user = userRepository.findRandomUser();
                userAddress.setUser(user);
                userAddress.setAddress(address);
                userAddress.setBilling(faker.bool().bool());
//            user.getUserAddresses().add(userAddress);
//            address.getUserAddresses().add(userAddress);
                userAddressRepository.save(userAddress);
            }
        }
    }

    private void createStationLocalisations(){
        Faker faker = new Faker(Locale.of("fr"));
        for(long i = 51L ; i<=100L;i++){
            if(!addressRepository.existsById(i)) {
                Address address = new Address();
                address.setLongitude((Math.random() * (8.135000 - 2.315800)) + 2.315800);
                address.setLatitude((Math.random() * (51.052100 - 42.195800)) + 42.195800);
                address.setStreetNumber(faker.address().streetAddressNumber());
                address.setStreet(faker.address().streetAddress());
                address.setCity(faker.address().city());
                address.setZipCode(faker.address().zipCode());
                User user = userRepository.findRandomUser();
                address.setOwner(user);
                addressRepository.save(address);
            }
        }
    }

    private void createPowers(){
        Faker faker = new Faker(Locale.of("fr"));
        List<Float> powers = List.of(4f,7.4f,22f,43.5f,80f);
        for(long i = 1L ; i<=5L;i++){
            if(!powerRepository.existsById(i)) {
                Power power = new Power();
                power.setAmount(powers.get((int) Math.floor(Math.random() * 5)));
                powerRepository.save(power);
            }
        }
    }



    private void createStations(){
        Faker faker = new Faker(Locale.FRANCE);
        for(long i = 1L ; i<=50L;i++){
            if(!chargingStationRepository.existsById(i)) {
                ChargingStation station = new ChargingStation();
                station.setName("borne " + i);
                station.setInstruction(faker.address().fullAddress());
                station.setOnStand(faker.bool().bool());
                station.setPower(powerRepository.findById((long) Math.ceil((Math.random() * 5))).get());
                Address address = addressRepository.findById(i + 50).get();
                station.setAddress(address);
//            address.getChargingStations().add(station);
                station.setAutoAcceptBooking(false);
                chargingStationRepository.save(station);
            }
        }
    }

}
