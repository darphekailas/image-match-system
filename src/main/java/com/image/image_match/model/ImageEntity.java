package com.image.image_match.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "images")
public class ImageEntity {

    @Id
    private String id;

    private String imageName;
    private String imageHash;

    // ✅ STORE ONLY RAW BYTES
    private byte[] imageData;
}
