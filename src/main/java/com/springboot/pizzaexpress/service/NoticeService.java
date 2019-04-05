package com.springboot.pizzaexpress.service;

import com.springboot.pizzaexpress.bean.Notice;

import java.util.List;

/**
 * Created by sts on 2019/3/10.
 */
public interface NoticeService {
    public void updateNoticeStatus(int noticeId,String newStatus);
//
    public String getAllUnreadNotices(int shopId);

//    public String getAllNotice(int shopId);
    public String getAllReadNotices(int shopId);

}
