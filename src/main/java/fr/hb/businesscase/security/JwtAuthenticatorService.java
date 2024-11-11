package fr.hb.businesscase.security;



import fr.hb.businesscase.custom_response.JwtResponse;
import fr.hb.businesscase.dto.UserLoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JwtAuthenticatorService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ResponseEntity<JwtResponse> authenticate(UserLoginDTO userLoginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getEmail(),
                            userLoginDto.getPassword()
                    )
            );

            String token = jwtService.generateToken(userLoginDto.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
