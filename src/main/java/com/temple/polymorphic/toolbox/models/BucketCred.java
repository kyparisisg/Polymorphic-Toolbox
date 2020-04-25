package com.temple.polymorphic.toolbox.models;

import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "BucketCred")
public class BucketCred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="bucket_name")
    @NotNull
    private String bucketName;
    @Column(name="private_key")
    @NotNull
    private String privateKey;
    @Column(name="public_key")
    @NotNull
    private String publicKey;

    public BucketCred() {
        super();
    }

    public BucketCred(@NotNull String bucketName, @NotNull String privateKey, @NotNull String publicKey) {
        this.id = id;
        this.bucketName = bucketName;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public BucketCred(Long id, @NotNull String bucketName, @NotNull String privateKey, @NotNull String publicKey) {
        this.id = id;
        this.bucketName = bucketName;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
