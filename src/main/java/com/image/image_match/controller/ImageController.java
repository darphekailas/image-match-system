package com.image.image_match.controller;

import com.image.image_match.dto.ImageMatchResponse;
import com.image.image_match.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            return imageService.saveImage(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error uploading image";
        }
    }

    // @PostMapping("/match")
    // public String matchImage(@RequestParam("file") MultipartFile file) {
    //     try {
    //         return imageService.matchImage(file);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return "Error matching image";
    //     }
    // }

//     @PostMapping("/match")
// public ImageMatchResponse matchImage(@RequestParam("file") MultipartFile file) {
//     try {
//         return imageService.matchImage(file);
//     } catch (Exception e) {
//         e.printStackTrace();
//         return new ImageMatchResponse(false, "Error", null, null);
//     }
// }

@PostMapping("/match")
public ImageMatchResponse matchImage(@RequestParam("file") MultipartFile file) {
    try {
        return imageService.matchImage(file);
    } catch (Exception e) {
        e.printStackTrace();
        return new ImageMatchResponse(
                false,
                "Error matching image",
                null,
                null
        );
    }
}
}
