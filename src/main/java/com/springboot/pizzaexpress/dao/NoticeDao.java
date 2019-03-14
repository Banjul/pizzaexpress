//package com.springboot.pizzaexpress.dao;
//
//import com.springboot.pizzaexpress.bean.Notice;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * Created by sts on 2019/3/10.
// */
//@Component
//@Repository
//public interface NoticeDao {
//
//
//    @Transactional
//    @Modifying
//    @Query(value = "insert into notice(title,content,status,time,label,shop_id) values(?1,?2,?3,?4,?5,?6)",nativeQuery = true)
//    void insertNotice(String title,String content,String status,String time, String label,int shop_id);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update notice set status = ?1 where notice_id= ?2",nativeQuery = true)
//    void updateNotice(String status, int noticeId);
//
//    @Query(value = "select * from notice where shop_id = ?1 and status = ?2",nativeQuery = true)
//    List<Notice> getAllUnreadNotices(int shopId, String status);
//
//}
