package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.Transactions;
import com.temple.polymorphic.toolbox.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long>{
    public static final String FIND_USER_BY_EMAIL = "SELECT u FROM Transactions t" +
            " INNER JOIN t.user u" +
            " WHERE u.email=?1";

    public static final String FIND_SRC_SERVER_BY_SERVER_ID = "SELECT s FROM Transactions t" +
            " INNER JOIN t.src_server s" +
            " WHERE s.id=?1";

    public static final String FIND_DST_SERVER_BY_SERVER_ID = "SELECT s FROM Transactions t" +
            " INNER JOIN t.dst_server s" +
            " WHERE s.id=?1";

    public static final String DELETE_BY_USER="DELETE FROM Transactions t" +
            " WHERE t.user=?1";

    public static final String DELETE_BY_SERVER="DELETE FROM Transactions t" +
            " WHERE t.src_server=?1 OR t.dst_server=?1";


    @Query(FIND_USER_BY_EMAIL)
    public User findUserByEmail(String email);

    @Query(FIND_SRC_SERVER_BY_SERVER_ID)
    public Server findSrcServerById(Long id);

    @Query(FIND_DST_SERVER_BY_SERVER_ID)
    public Server findDstServerById(Long id);

    @Transactional
    @Modifying
    @Query(DELETE_BY_USER)
    public void deleteByUser(User user);

    @Transactional
    @Modifying
    @Query(DELETE_BY_SERVER)
    public void deleteByServer(Server server);
}