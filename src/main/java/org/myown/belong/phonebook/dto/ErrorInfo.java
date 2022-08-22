package org.myown.belong.phonebook.dto;

public class ErrorInfo {
    private String url;
    private String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }

    public ErrorInfo() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getUrl() {
        return url;
    }

    public String getEx() {
        return ex;
    }
}
