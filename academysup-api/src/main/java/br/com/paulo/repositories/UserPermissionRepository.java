package br.com.paulo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.paulo.entities.UserPermission;
import br.com.paulo.entities.idclass.UserPermissionId;
import jakarta.transaction.Transactional;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, UserPermissionId> {
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO user_permission (id_user, id_permission) VALUES (:userId, :permissionId)", nativeQuery = true)
    void insertUserPermission(@Param("userId") Long userId, @Param("permissionId") Long permissionId);
}
