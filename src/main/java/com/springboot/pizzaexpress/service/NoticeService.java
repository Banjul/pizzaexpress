package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/10.
 */
public interface NoticeService {

    public void updateNoticeStatus(int noticeId,String newStatus);

    public String getAllUnreadNotices(int shopId);
}
