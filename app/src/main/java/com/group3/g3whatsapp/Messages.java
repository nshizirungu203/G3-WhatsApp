package com.group3.g3whatsapp;

import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("message")
    private String message;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("uId")
    private String uId;

    public Messages(String message, String timestamp, String uId) {
        this.message = message;
        this.timestamp = timestamp;
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getuId() {
        return uId;
    }
}
