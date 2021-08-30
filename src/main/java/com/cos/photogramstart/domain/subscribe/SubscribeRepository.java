package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe,Long>{

    @Modifying //insert, delete, update를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!
    @Query(value = "insert into subscribe(fromUserId,toUserId,createDate) values (:fromUserId,:toUserId,now())", nativeQuery = true)
    void mSubscribe(Long fromUserId, Long toUserId);

    @Modifying
    @Query(value = "delete from subscribe where fromUserId = :fromUserId and toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(Long fromUserId, long toUserId);
}
