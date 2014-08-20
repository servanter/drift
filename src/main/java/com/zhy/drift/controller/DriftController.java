package com.zhy.drift.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zhy.drift.bean.Request;
import com.zhy.drift.bean.Response;
import com.zhy.drift.exception.InValidException;
import com.zhy.drift.service.EntranceService;
import com.zhy.drift.util.DriftUtil;
import com.zhy.drift.util.Tools;

/**
 * 详细信息
 * 
 * @author zhy
 * 
 */
public class DriftController extends MultiActionController {

    public static Logger logger = Logger.getLogger(DriftController.class);

    @Autowired
    private EntranceService entranceService;

    public ModelAndView drift(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!isPost(request)) {
                String token = "555";
                String signature = request.getParameter("signature");// 微信加密签名
                String timestamp = request.getParameter("timestamp");// 时间戳
                String nonce = request.getParameter("nonce");// 随机数
                String echostr = request.getParameter("echostr");//
                System.out.println("token:[" + token + "],signature:[" + signature + "],timestamp:[" + timestamp
                        + "],echostr:[" + echostr + "]");
                // 验证
                if (Tools.checkSignature(token, signature, timestamp, nonce)) {
                    response.getWriter().write(echostr);
                }
            } else {
                response.setContentType("text/html;charset=utf-8");
                Document document = Tools.getDocumentByInputStream(request.getInputStream());
                logger.info("[Request]:" + document.asXML());
                Request req = Request.generate(document);
                Response resp = entranceService.dispatch(req);
                logger.info("[Response]:" + resp.toWeChat());
                response.getWriter().write(resp.toWeChat());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public ModelAndView webDrift(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/x-www-form-urlencoded");
        response.setCharacterEncoding("UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        String phone = request.getParameter("user_phone");
        String content = request.getParameter("bottle_content");
        try {
            content = URLDecoder.decode(content, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            DriftUtil.checkNullAndInvalidStr(phone);
            DriftUtil.checkNullAndInvalidStr(content);
        } catch (InValidException e) {
            e.printStackTrace();
            return new ModelAndView("error.jsp", "result", "error");
        }
        content = content.toLowerCase();
        content = content.trim();
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = request.getHeader("x-forwarded-for");
        }
        Request req = new Request();
        req.setContent(content);
        req.setFromUserName(phone);
        Response res = entranceService.dispatch(req);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(res.getRequest().getResult());
        out.flush();
        out.close();
        return null;
    }
    
    public boolean isPost(HttpServletRequest request) {
        return "POST".equals(request.getMethod().toUpperCase());
    }
}
