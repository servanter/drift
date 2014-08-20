package com.zhy.drift.service.impl;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.SuperTest;
import com.zhy.drift.bean.MsgType;
import com.zhy.drift.bean.Request;
import com.zhy.drift.bean.Response;
import com.zhy.drift.service.EntranceService;

public class EntranceServiceImplTest extends SuperTest {

    public static Logger logger = Logger.getLogger(EntranceServiceImplTest.class);

    @Autowired
    private EntranceService entranceService;

    @Test
    public void testDispatch() {
        Response response = entranceService.dispatch(new Request().setContent("有人吗").setFromUserName("tom")
                .setToUserName("lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());
        response = entranceService.dispatch(new Request().setContent("zl小岩。24，男，北京").setFromUserName("tom")
                .setToUserName("lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("有人吗").setFromUserName("tom").setToUserName("lily")
                .setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("ZL大好50岁男厦门").setFromUserName("aaaaa")
                .setToUserName("lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("cx").setFromUserName("aaaaa").setToUserName(
                "lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("@小岩你好啊").setFromUserName("aaaaa").setToUserName(
                "lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("cx").setFromUserName("tom").setToUserName("lily")
                .setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("@大好哈哈，你也好").setFromUserName("tom")
                .setToUserName("lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("cx").setFromUserName("aaaaa").setToUserName(
                "lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("@小岩你多大了？").setFromUserName("aaaaa")
                .setToUserName("lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

        response = entranceService.dispatch(new Request().setContent("cx").setFromUserName("tom").setToUserName("lily").setType(
                MsgType.TEXT));
        logger.debug(response.toWeChat());
        
        response = entranceService.dispatch(new Request().setContent("@大好.24了，你呢？").setFromUserName("tom").setToUserName("lily").setType(
                MsgType.TEXT));
        logger.debug(response.toWeChat());

         response = entranceService.dispatch(new Request().setContent("qd大好").setFromUserName("tom").setToUserName("lily").setType(
                MsgType.TEXT));
        logger.debug(response.toWeChat());
    }

    @Test
    public void testDispatchXX() {
        Response response = entranceService.dispatch(new Request().setContent("zl小岩。24，男，北京").setFromUserName("tom")
                .setToUserName("lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());
        response = entranceService.dispatch(new Request().setContent("xx小岩").setFromUserName("tom").setToUserName(
                "lily").setType(MsgType.TEXT));
        logger.debug(response.toWeChat());

    }

}
