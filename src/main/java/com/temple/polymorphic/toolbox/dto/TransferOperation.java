package com.temple.polymorphic.toolbox.dto;

public class TransferOperation {

    private String email;
    private Long srcServerId;

    public TransferOperation() {
        super();
    }

    public TransferOperation(String email, Long srcServerId) {
        this.email = email;
        this.srcServerId = srcServerId;
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
}
