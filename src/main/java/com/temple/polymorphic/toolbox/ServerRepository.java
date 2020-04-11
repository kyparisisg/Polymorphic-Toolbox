package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    public static final String FIND_BY_IP = "SELECT s FROM Server s WHERE s.ip=?1 ";

    @Query(FIND_BY_IP)
    public Server findByIp(String ip);
}
