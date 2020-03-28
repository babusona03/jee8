package dev.ls.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.ws.rs.FormParam;

import dev.ls.util.AbstractUserListener;

@Entity
@EntityListeners({AbstractUserListener.class})
@Table(name="CREDENTIAL")
public class UserCredentials extends  AbstractUser implements Serializable{
	@Transient
	private static final long serialVersionUID = 1L;

	@FormParam("username")
	@Column(name="USERNAME")
	private String userName;
	
	@FormParam("password")
	@Column(name="PASSWORD")
	private String password;
	
	
	public UserCredentials() {		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
