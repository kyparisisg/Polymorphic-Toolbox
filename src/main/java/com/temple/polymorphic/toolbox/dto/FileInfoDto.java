package com.temple.polymorphic.toolbox.dto;

public class FileInfoDto {

    private String file_name;
    private String ipv4;
    private String user;    // how can I make this visible to me when the user logs in.
    private String encryption_token;
    private String s3dir;
    private String bucket

    public FileInfoDto() {

    }

    public FileInfoDto(String file_name) {
        this.file_name = file_name;
    }

    public FileInfoDto(String file_name, String ipv4, String user, String encryption_token) {
        this.file_name = file_name;
        this.ipv4 = ipv4;
        this.user = user;
        this.encryption_token = encryption_token;
        this.s3dir = s3dir;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getUser() {
        return user;
    }

    public String getS3dir(){return s3dir;}

    public void setUser(String user) {
        this.user = user;
    }

    public void setS3dir(String s3dir){this.s3dir = s3dir;}

    public String getEncryption_token() {
        return encryption_token;
    }

    public void setEncryption_token(String encryption_token) {
        this.encryption_token = encryption_token;
    }
}
