package com.cos.photogramstart.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository extends ImageRepository Interface
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
