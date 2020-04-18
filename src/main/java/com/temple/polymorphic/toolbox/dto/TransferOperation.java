package com.temple.polymorphic.toolbox.dto;

public class TransferOperation {

    private String email;
    private Long srcServerId;
    private String filePath;
    private Long dstServerId;
    private String targetPath;

    public TransferOperation() {
        super();
    }

    public TransferOperation(String email, Long srcServerId) {
        this.email = email;
        this.srcServerId = srcServerId;
    }

    public TransferOperation(String email, Long srcServerId, String filePath) {
        this.email = email;
        this.srcServerId = srcServerId;
        this.filePath = filePath;
    }

    public TransferOperation(String email, Long srcServerId, String filePath, Long dstServerId) {
        this.email = email;
        this.srcServerId = srcServerId;
        this.filePath = filePath;
        this.dstServerId = dstServerId;
    }

    public TransferOperation(String email, Long srcServerId, String filePath, Long dstServerId, String targetPath) {
        this.email = email;
        this.srcServerId = srcServerId;
        this.filePath = filePath;
        this.dstServerId = dstServerId;
        this.targetPath = targetPath;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getDstServerId() {
        return dstServerId;
    }

    public void setDstServerId(Long dstServerId) {
        this.dstServerId = dstServerId;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
