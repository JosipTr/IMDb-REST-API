package com.example.imdb.authentication.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.imdb.authentication.entity.UserEntity;

@Document(collection = "users")
public class UserModel {
	@Id
	private String id;
	@Indexed(unique = true)
	private  String email;
	private  String password;

	public UserModel(String id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public UserModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserModel() {
		
	}

	public static UserModel fromRegisterRequest(RegisterRequest request) {
		return new UserModel(request.email(), request.password());
	}

	public UserEntity toUserEntity() {
		return new UserEntity(id, email, password);
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
		UserModel other = (UserModel) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

}
