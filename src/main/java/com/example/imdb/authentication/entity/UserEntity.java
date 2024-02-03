package com.example.imdb.authentication.entity;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.imdb.authentication.UserPrincipal;
import com.example.imdb.authentication.dto.RegisterDto;
import com.example.imdb.authentication.dto.UserDto;

@Document(collection = "users")
public class UserEntity {
	@Id
	private String id;
	@Indexed(unique = true)
	private  String email;
	private  String password;

	public UserEntity(String id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public UserEntity(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserEntity() {};

	public static UserEntity fromRegisterDto(RegisterDto request) {
		return new UserEntity(request.email(), request.password());
	}
	
	public UserPrincipal toUserPrincipal() {
		return new UserPrincipal(id, email, password);
	}
	
	public UserDto toUserDto() {
		return new UserDto(id, email);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

}
