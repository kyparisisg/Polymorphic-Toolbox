package com.temple.polymorphic.toolbox.dto;

public class TransferOperation {

    private String email;
    private Long srcServerId;
    private String fileName;
    private Long dstServerId;
    private String targetPath;

    public TransferOperation() {
        super();
    }

    public TransferOperation(String email, Long srcServerId) {
        this.email = email;
        this.srcServerId = srcServerId;
    }

    public TransferOperation(String email, Long srcServerId, String fileName) {
        this.email = email;
        this.srcServerId = srcServerId;
        this.fileName = fileName;
    }

    public TransferOperation(String email, Long srcServerId, String fileName, Long dstServerId) {
        this.email = email;
        this.srcServerId = srcServerId;
        this.fileName = fileName;
        this.dstServerId = dstServerId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSrcServerId() {
        return srcServerId;
    }

    public void setSrcServerId(Long srcServerId) {
        this.srcServerId = srcServerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getDstServerId() {
        return dstServerId;
    }

    public void setDstServerId(Long dstServerId) {
        this.dstServerId = dstServerId;
    }

}
