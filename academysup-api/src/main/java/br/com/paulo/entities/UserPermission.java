package br.com.paulo.entities;

import java.io.Serializable;

import br.com.paulo.entities.idclass.UserPermissionId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(UserPermissionId.class)
public class UserPermission implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_permission")
    private Permission permission;
    
	public UserPermission(User user, Permission permission) {
		this.user = user;
		this.permission = permission;
	}
	
	public UserPermission() {}

	public User getUser() {
		return user;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
    
    
}
