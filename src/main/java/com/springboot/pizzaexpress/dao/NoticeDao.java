package com.springboot.pizzaexpress.dao;

import com.springboot.pizzaexpress.bean.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sts on 2019/3/10.
 */
@Component
@Repository
public interface NoticeDao extends JpaRepository<Notice,String> {


    @Transactional
    @Modifying
    @Query(value = "insert into notice(content,label,title,shop_id,status,notice_time) values (?1,?2,?3,?4,?5,?6) ",nativeQuery = true)
    void insertNotice(String content,String label ,String title, int shop_id, String status, String noticeTime);

    @Transactional
    @Modifying
    @Query(value = "update notice set status = ?1 where notice_id= ?2",nativeQuery = true)
    void updateNotice(String status, int noticeId);

    @Query(value = "select * from notice where shop_id = ?1 and status = ?2 ORDER BY notice_time ASC",nativeQuery = true)
    List<Notice> getAllUnreadNotices(int shopId, String status);

    @Query(value = "select * from notice where shop_id = ?1",nativeQuery = true)
    List<Notice> queryAllNotice(int shopId);

    @Query(value = "select * from notice where shop_id = ?1 and status =?2 and title = ?3",nativeQuery = true)
    List<Notice> getNoticeByStatusContent(int shopId, String status, String title);
}
