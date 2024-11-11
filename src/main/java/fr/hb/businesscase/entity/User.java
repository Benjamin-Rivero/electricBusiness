package fr.hb.businesscase.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.businesscase.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import fr.hb.businesscase.json_views.JsonViewUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User implements UserDetails, SluggerInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@JsonView(JsonViewUser.Email.class)
	@Column(unique = true)
	private String email;

	@JsonView(JsonViewUser.Password.class)
	private String password;

	@JsonView(JsonViewUser.LastName.class)
	private String lastName;

	@JsonView(JsonViewUser.FirstName.class)
	private String firstName;

	@JsonView(JsonViewUser.Phone.class)
	private String phone;

	@JsonView(JsonViewUser.BirthDate.class)
	private LocalDate birthDate;

	private String activationToken;

	private LocalDateTime activationTokenSentAt;

	@JsonView(JsonViewUser.CreatedAt.class)
	private LocalDateTime createdAt;

	private String roles;

	@JsonView(JsonViewUser.Slug.class)
	private String slug;


	@OneToMany(mappedBy = "userFrom")
	private List<UserReview> userReviewsReceived = new ArrayList<>();

	@OneToMany(mappedBy = "userTo")
	private List<UserReview> userReviewsSent = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Booking> bookings = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<UserAddress> userAddresses = new ArrayList<>();

	@OneToMany(mappedBy = "owner")
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Review> reviews = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getField() {
		return getFullName();
	}

	public String getFullName() {
		return firstName+" "+lastName;
	}
}