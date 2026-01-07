package com.image.image_match.service;

import com.image.image_match.dto.ImageMatchResponse;
import com.image.image_match.model.ImageEntity;
import com.image.image_match.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String generateImageHash(MultipartFile file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(file.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

   public String saveImage(MultipartFile file) throws Exception {

    String hash = generateImageHash(file);

    if (imageRepository.findByImageHash(hash).isPresent()) {
        return "Image already exists";
    }

    ImageEntity image = new ImageEntity();
    image.setImageName(file.getOriginalFilename());
    image.setImageHash(hash);
    image.setImageData(file.getBytes()); // ✅ store bytes

    imageRepository.save(image);

    return "Image stored successfully";
}


    // public String matchImage(MultipartFile file) throws Exception {
    //     String hash = generateImageHash(file);

    //     Optional<ImageEntity> matched =
    //             imageRepository.findByImageHash(hash);

    //     if (matched.isPresent()) {
    //         return "✅ Match Found with image: " +
    //                 matched.get().getImageName();
    //     } else {
    //         return "❌ No Match Found";
    //     }
    // }
    // public ImageMatchResponse matchImage(MultipartFile file) throws Exception {

    // String hash = generateImageHash(file);

    // Optional<ImageEntity> matchedImage =
    //         imageRepository.findByImageHash(hash);

    // // ✅ Uploaded image → Base64 (for UI display)
    // String uploadedBase64 = Base64.getEncoder()
    //         .encodeToString(file.getBytes());

    // if (matchedImage.isPresent()) {

    //     // ✅ Stored image → Base64 (convert from byte[])
    //     String storedBase64 = Base64.getEncoder()
    //             .encodeToString(matchedImage.get().getImageData());

    //     return new ImageMatchResponse(
    //             true,
    //             "Image matched successfully",
    //             uploadedBase64,
    //             storedBase64
    //     );
    // } else {
    //     return new ImageMatchResponse(
    //             false,
    //             "Image match not found",
    //             uploadedBase64,
    //             null
    //     );
    // }
    // }

    public ImageMatchResponse matchImage(MultipartFile file) throws Exception {

    String hash = generateImageHash(file);

    Optional<ImageEntity> matchedImage =
            imageRepository.findByImageHash(hash);

    // ✅ uploaded image → base64
    String uploadedBase64 = Base64.getEncoder()
            .encodeToString(file.getBytes());

    if (matchedImage.isPresent()) {

        // ✅ stored image bytes → base64
        byte[] storedBytes = matchedImage.get().getImageData();
       
        // ✅ VERY IMPORTANT NULL CHECK
        if (storedBytes == null || storedBytes.length == 0) {
            return new ImageMatchResponse(
                    false,
                    "Stored image data is missing. Please re-upload image.",
                    uploadedBase64,
                    null
            );
        }

        String storedBase64 = Base64.getEncoder()
                .encodeToString(storedBytes);

        return new ImageMatchResponse(
                true,
                "Image matched successfully",
                uploadedBase64,
                storedBase64
        );

    } else {
        return new ImageMatchResponse(
                false,
                "Image match not found",
                uploadedBase64,
                null
        );
    }
}

}
