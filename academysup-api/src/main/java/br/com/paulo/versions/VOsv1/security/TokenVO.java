package br.com.paulo.versions.VOsv1.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idUser;
	private String username;
	private Boolean authenticated;
	private Date created;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
	
	public TokenVO(Long idUser, String username, Boolean authenticated, Date created, Date expiration,
			String accessToken, String refreshToken) {
		this.idUser = idUser;
		this.username = username;
		this.authenticated = authenticated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public TokenVO() {}
	
	public Long getId_user() {
		return idUser;
	}

	public void setId_user(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public Date getCreated() {
		return created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, authenticated, created, expiration, refreshToken, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenVO other = (TokenVO) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(authenticated, other.authenticated)
				&& Objects.equals(created, other.created) && Objects.equals(expiration, other.expiration)
				&& Objects.equals(refreshToken, other.refreshToken) && Objects.equals(username, other.username);
	}


}
