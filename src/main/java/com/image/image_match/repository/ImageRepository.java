package com.image.image_match.repository;

import com.image.image_match.model.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageRepository
        extends MongoRepository<ImageEntity, String> {

    Optional<ImageEntity> findByImageHash(String imageHash);
}
