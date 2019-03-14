//package com.springboot.pizzaexpress.serviceImpl;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.springboot.pizzaexpress.bean.Notice;
//import com.springboot.pizzaexpress.dao.NoticeDao;
//import com.springboot.pizzaexpress.service.NoticeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * Created by sts on 2019/3/10.
// */
//
//@Service
//public class NoticeServiceImp implements NoticeService {
//
//    @Autowired
//    private NoticeDao noticeDao;
//
//    @Override
//    public void updateNoticeStatus(int noticeId, String newStatus) {
//        noticeDao.updateNotice(newStatus,noticeId);
//    }
//
//    @Override
//    public String getAllUnreadNotices(int shopId) {
//        JSONArray unreadNoticeArray = new JSONArray();
//        JSONObject unreadNoticeData = new JSONObject();
//        JSONObject dataJson = new JSONObject();
//
//        String status = "未读";
//        List<Notice> unreadNotices = noticeDao.getAllUnreadNotices(shopId,status);
//        if (unreadNotices.size() >0) {
//            for (Notice unreadNotice : unreadNotices) {
//                JSONObject unreadNoticeJSON = new JSONObject();
//                unreadNoticeJSON.put("title",unreadNotice.getNoticeTitle());
//                unreadNoticeJSON.put("content",unreadNotice.getContent());
//                unreadNoticeJSON.put("time",unreadNotice.getTime());
//                unreadNoticeJSON.put("status",unreadNotice.getStatus());
//                unreadNoticeJSON.put("label",unreadNotice.getLabel());
//
//                unreadNoticeArray.add(unreadNoticeJSON);
//            }
//        }
//
//        unreadNoticeData.put("count",unreadNotices.size());
//        unreadNoticeData.put("data",unreadNoticeArray);
//        dataJson.put("unreadNoticeData",unreadNoticeData);
//        return dataJson.toJSONString();
//
//    }
//}
