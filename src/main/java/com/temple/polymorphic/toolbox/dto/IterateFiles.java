package com.temple.polymorphic.toolbox.dto;

public class IterateFiles {
    private String name;
    private String fileInfo;

    public IterateFiles( ) {
        super();
    }

    public IterateFiles(String name ) {
        this.name = name;
    }

    public IterateFiles(String name, String fileInfo) {
        this.name = name;
        this.fileInfo = fileInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }
}
