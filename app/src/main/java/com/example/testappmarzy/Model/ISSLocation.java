package com.example.testappmarzy.Model;

/**
 * Pojo de ISSLocation
 */
public class ISSLocation {
    private String message;
    private String timestamp;
    private ISSPosition iss_position;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ISSPosition getIss_position() {
        return iss_position;
    }

    public void setIss_position(ISSPosition iss_position) {
        this.iss_position = iss_position;
    }
}
