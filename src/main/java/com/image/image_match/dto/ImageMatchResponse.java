package com.image.image_match.dto;

public class ImageMatchResponse {

    private boolean matched;
    private String message;
    private String uploadedImage;
    private String matchedImage;

    public ImageMatchResponse(
            boolean matched,
            String message,
            String uploadedImage,
            String matchedImage) {
        this.matched = matched;
        this.message = message;
        this.uploadedImage = uploadedImage;
        this.matchedImage = matchedImage;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(String uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    public
    String getMatchedImage() {
        return matchedImage;
    }
    public void setMatchedImage(String matchedImage) {
        this.matchedImage = matchedImage;
    }

    
}