package com.zhy.drift.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zhy.drift.bean.User;
import com.zhy.drift.exception.InValidException;

/**
 * 工具类
 * 
 * @author zhy
 * 
 */
public class DriftUtil {

    private static String regx = "\\d+";

    /**
     * 验证字符串是否有效
     * 
     * @param str
     * @throws InValidException
     */
    public static void checkNullAndInvalidStr(String str) throws InValidException {
        if (str == null || str.trim().length() == 0) {
            throw new InValidException("Invalid string.");
        }
    }

    /**
     * 抽取信息
     * 
     * @param message
     * @param user
     */
    public static void extractInfo(String content, User user) {
        String remainContent = content;
        remainContent = replaceUsedStr(remainContent, extractCity(remainContent, user));
        remainContent = replaceUsedStr(remainContent, "人");
        remainContent = replaceUsedStr(remainContent, extractAge(remainContent, user));
        remainContent = replaceUsedStr(remainContent, "岁");
        remainContent = replaceUsedStr(remainContent, extractSex(remainContent, user));
        remainContent = replaceUsedStr(remainContent, "zl");
        remainContent = replace(remainContent);
        remainContent = replaceUsedStr(remainContent, extractNick(remainContent, user));
    }

    /**
     * 抽取城市
     * 
     * @param content
     * @return
     */
    private static String extractCity(String content, User user) {
        String[] cities = TemplateUtil.getCities();
        for (String city : cities) {
            if (content.contains(city)) {
                user.setCity(city);
                return city;
            }
        }
        return "";
    }

    /**
     * 抽取年龄
     * 
     * @param message
     */
    private static String extractAge(String content, User user) {
        Matcher matcher = Pattern.compile(regx).matcher(content);
        while (matcher.find()) {
            String ageStr = matcher.group(0);
            user.setAge(Integer.parseInt(ageStr));
            return ageStr;
        }
        return "";
    }

    /**
     * 抽取性别
     * 
     * @param message
     */
    private static String extractSex(String content, User user) {
        String sex = "";
        if (content.contains("男")) {
            sex = "男";
            user.setSex("帅哥");
        } else {
            sex = "女";
            user.setSex("美女");
        }
        return sex;
    }

    /**
     * 抽取昵称
     * 
     * @param message
     */
    private static String extractNick(String content, User user) {
        user.setName(content.trim());
        return content.trim();
    }

    /**
     * 移除抽取过的信息
     * 
     * @param message
     * @param str
     */
    private static String replaceUsedStr(String content, String str) {
        return content.replace(str, "");
    }

    /**
     * 获取Map中有效的值,如果不存在,则返回null
     * 
     * @param map
     * @param key
     * @return
     */
    public static String getMapValue(Map<String, Object> map, String key) {
        return map.get(key) != null ? map.get(key).toString() : null;
    }

    /**
     * 获取Map中有效的值,如果不存在,返回0L
     * 
     * @param map
     * @param key
     * @return
     */
    public static Long getMapValue2Long(Map<String, Object> map, String key) {
        if (getMapValue(map, key) != null) {
            return Long.parseLong(getMapValue(map, key));
        }
        return 0L;
    }
    
    /**
     * 删除多余的空格 换行
     * TODO 后期应该用正则表达式
     * @param str
     * @return
     */
    public static String removeRemain(String str){
        if(str != null){
            return str.trim().replace("\n", "").trim();
        }
        return null;
    }

    public static String replace(String str){
        if(str != null){
            return str.replace("：", "").replace(":", "").replace("，", "").replace(",", "").replace("。", "").replace(".", "").replace("!", "").replace("！", "").toLowerCase();
        }
        return "";
    }
    
    public static String dueStr(String str){
        if(str != null){
            return str.replace("：", ":").replace("，", ",").replace("。", ".").toLowerCase();
        }
        return "";
    }
}
