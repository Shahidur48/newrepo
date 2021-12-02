package edu.baylor.cs.se.hibernate.model;

public class UserResponse {
    String content;
    String content2;
    String content3;

    public UserResponse() {
    }

    public String getContent() {
        return content;
    }

    public String getContent2() {
        return content2;
    }

    public String getContent3() {
        return content3;
    }

    public UserResponse(String content, String content2, String content3) {
        this.content = content;
        this.content2 = content2;
        this.content3 = content3;
    }

}