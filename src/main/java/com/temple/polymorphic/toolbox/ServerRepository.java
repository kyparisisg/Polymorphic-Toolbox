package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    public static final String FIND_BY_IP = "SELECT s FROM Server s WHERE s.ip=?1 ";
    public static final String UPDATE_HEALTH = "UPDATE Server s SET s.health=?1 WHERE s.id=?2";


    @Query(FIND_BY_IP)
    public Server findByIp(String ip);

    @Transactional
    @Modifying
    @Query(UPDATE_HEALTH)
    public void updateServerHealth(int health, Long id);
}
