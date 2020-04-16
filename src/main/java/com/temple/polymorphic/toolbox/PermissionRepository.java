package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.User;
import com.temple.polymorphic.toolbox.models.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.Permission;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long>{
    public static final String FIND_ALL_BY_EMAIL = "SELECT p FROM Permissions p" +
            " INNER JOIN p.user u" +
            " WHERE u.email=?1";

    public static final String FIND_BY_IDS= "SELECT p FROM Permissions p" +
            " INNER JOIN p.user u" +
            " INNER JOIN p.server s" +
            " WHERE u.id=?1 AND s.id=?2";

    public static final String FIND_USER_BY_EMAIL = "SELECT u FROM Permissions p" +
            " INNER JOIN p.user u" +
            " WHERE u.email=?1";

    public static final String FIND_SERVER_BY_SERVER_ID = "SELECT s FROM Permissions p" +
            " INNER JOIN p.server s" +
            " WHERE s.id=?1";

    public static final String FIND_ALL_SERVERS_BY_EMAIL = "SELECT s FROM Permissions p" +
            " INNER JOIN p.user u" +
            " INNER JOIN p.server s" +
            " WHERE u.email=?1";

    public static final String DELETE_BY_USER="DELETE FROM Permissions p" +
            " WHERE p.user=?1";

    public static final String DELETE_BY_SERVER="DELETE FROM Permissions p" +
            " WHERE p.server=?1";

    @Query(FIND_ALL_BY_EMAIL)
    public List<Permissions> findAllByEmail(String email);

    @Query(FIND_BY_IDS)
    public Permissions findByIds(Long user_id, Long server_id);

    @Query(FIND_USER_BY_EMAIL)
    public User findUserByEmail(String email);

    @Query(FIND_SERVER_BY_SERVER_ID)
    public Server findServerById(Long id);

    @Query(FIND_ALL_SERVERS_BY_EMAIL)
    public List<Server> findAllServersByEmail(String email);

    @Transactional
    @Modifying
    @Query(DELETE_BY_USER)
    public void deleteByUser(User user);

    @Transactional
    @Modifying
    @Query(DELETE_BY_SERVER)
    public void deleteByServer(Server server);
}