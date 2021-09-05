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

    @Query(value =  "select t1.* "                                      +
                    "  from image t1 "                                  +
                    "     , ( "                                         +
                    "         select imageId,count(*) as likeCount "    +
                    "           from likes "                            +
                    "          where 1=1 "                              +
                    "          group by imageId "                       +
                    "       ) t2 "                                      +
                    "   where t1.id = t2.imageId "                      +
                    " order by t2.likeCount desc ", nativeQuery = true)
    public List<Image> mPopular();

}
