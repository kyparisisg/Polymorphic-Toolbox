package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long>{
    public static final String FIND_ALL_BY_EMAIL = "SELECT p FROM Permissions p" +
            " INNER JOIN p.user u" +
            " WHERE u.email=?1";

    @Query(FIND_ALL_BY_EMAIL)
    public List<Permissions> findAllByEmail(String email);
}
