package com.cos.photogramstart.domain.likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "insert into likes (userId,imageId,createDate) values (:principalId, :imageId, now()) ", nativeQuery = true)
    public void mLike(Long principalId, Long imageId);

    @Modifying
    @Query(value = "delete from likes where userId = :principalId and imageId = :imageId ", nativeQuery = true)
    public void mUnLike(Long principalId, Long imageId);

}
