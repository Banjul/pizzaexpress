package com.springboot.pizzaexpress.controller;

import com.springboot.pizzaexpress.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by sts on 2019/3/10.
 */
@RestController
@RequestMapping(value ="/Notice")
@Api("通知api")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "更改消息状态", notes = "需要，开始时间，结束时间")
    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间,shopId,deliverID,order_id的json", dataType = "JSON")
    @RequestMapping(value = "/updatenoticestatus", method = RequestMethod.POST)
    public void updateNoticeStatus (@RequestBody Map<String, Object> params) {
        String noticeid = params.get("noticeId").toString();
        int noticeId = Integer.parseInt(noticeid);
        String newStatus = params.get("status").toString();

        noticeService.updateNoticeStatus(noticeId,newStatus);
    }

    @ApiOperation(value = "查找所有未读信息", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/queryunreadnotices", method = RequestMethod.POST)
    public String queryUnreadNotices (@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);

        return noticeService.getAllUnreadNotices(shopId);
    }

    @ApiOperation(value = "查找所有已读信息", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/queryreadnotices", method = RequestMethod.POST)
    public String queryReadNotices (@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);

        return noticeService.getAllReadNotices(shopId);
    }
//    @ApiOperation(value = "查询所有信息", notes = "")
//    @ApiImplicitParam(name = "params", value = " ", dataType = "JSON")
//    @RequestMapping(value = "/getallnotice", method = RequestMethod.POST)
//    public String getAllNotice (@RequestBody Map<String, Object> params) {
//        String shopid = params.get("shopID").toString();
//        int shopId = Integer.parseInt(shopid);
//
//        return noticeService.getAllNotice(shopId);
//
//    }

}




