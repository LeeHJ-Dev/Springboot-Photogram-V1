package com.cos.photogramstart.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository extends ImageRepository Interface
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "select * from image where userId in (select toUserId from subscribe where fromUserId = :principalId) ",nativeQuery = true)
    public Page<Image> mStory(Long principalId, Pageable pageable);

}
