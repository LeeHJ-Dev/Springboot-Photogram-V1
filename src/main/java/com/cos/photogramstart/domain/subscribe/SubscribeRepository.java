package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * JpaRepository Extends -> SubscribeRepository Interface
 */
public interface SubscribeRepository extends JpaRepository<Subscribe,Long>{

    /**
     * 사용자가 특정 사용자를 구독처리할 때 네이티브 쿼리를 통해서 데이터 등록
     *  - 네이티브 쿼리를 작성할 떄 insert, delete, update 진행 시 @Modifying 설정.
     *  - 네이티브 쿼리인경우 nativeQuery = true.
     * @param fromUserId 사용자 userId
     * @param toUserId 구독자 userId
     */
    @Modifying //insert, delete, update를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!
    @Query(value = "insert into subscribe(fromUserId,toUserId,createDate) values (:fromUserId,:toUserId,now())", nativeQuery = true)
    public void mSubscribe(Long fromUserId, Long toUserId);


    /**
     * 사용자가 특정 사용자에 대해서 구독취소를 진행하는 경우
     * @param fromUserId 사용자 userId
     * @param toUserId 구독자 userId
     */
    @Modifying
    @Query(value = "delete from subscribe where fromUserId = :fromUserId and toUserId = :toUserId", nativeQuery = true)
    public void mUnSubscribe(Long fromUserId, long toUserId);

    /**
     * 사용자가 해당 구독자를 구독했는지 확인
     * @param principalId
     * @param pageUserId
     * @return
     */
    @Query(value = "select count(*) from subscribe where fromUserId = :principalId and toUserId = :pageUserId", nativeQuery = true)
    public int mSubscribeState(Long principalId,Long pageUserId);

    /**
     * 사용자가 구독하고 있는 구독자 총 건수
     * @param principalId
     * @return 구독자 건수
     */
    @Query(value = "select count(*) from subscribe where fromUserId = :pageUserId", nativeQuery = true)
    public int mSubscribeCount(Long pageUserId);

}
