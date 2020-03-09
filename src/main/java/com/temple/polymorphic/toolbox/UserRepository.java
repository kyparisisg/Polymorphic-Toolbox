package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public static final String FIND_BY_EMAIL = "SELECT u FROM User u WHERE u.email=?1 ";
    public static final String UPDATE_BY_EMAIL = "UPDATE User u SET u.password=?1 WHERE u.email=?2 ";


    @Query(FIND_BY_EMAIL)
    public User findByEmail(String email);

    @Query(UPDATE_BY_EMAIL)
    public void updateUserPassword(String password, String email);
}
