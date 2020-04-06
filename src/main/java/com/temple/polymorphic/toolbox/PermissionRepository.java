package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Server, Long>{

}
