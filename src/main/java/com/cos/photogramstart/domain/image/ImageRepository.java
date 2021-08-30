package com.cos.photogramstart.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 사용자가 사진 및 내용을 작성 후 등록한다.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
