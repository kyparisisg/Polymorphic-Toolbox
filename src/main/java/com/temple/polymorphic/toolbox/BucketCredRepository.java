package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.BucketCred;
import com.temple.polymorphic.toolbox.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BucketCredRepository extends JpaRepository<BucketCred, Long> {
    public static final String FIND_BY_BUCKET_NAME = "SELECT b FROM BucketCred b WHERE b.bucket_name=?1 ";

    @Query(FIND_BY_BUCKET_NAME)
    public User findByBucketName(String bucketName);
}
