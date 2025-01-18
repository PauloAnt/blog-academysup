package br.com.paulo.entities.idclass;

import java.io.Serializable;
import java.util.Objects;

public class UserPermissionId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long user;
    private Long permission;

    // Construtores, getters, setters, hashCode e equals
    public UserPermissionId() {}

    public UserPermissionId(Long user, Long permission) {
        this.user = user;
        this.permission = permission;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getPermission() {
        return permission;
    }

    public void setPermission(Long permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermissionId that = (UserPermissionId) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, permission);
    }
}
