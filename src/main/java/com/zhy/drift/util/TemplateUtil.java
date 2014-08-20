package com.zhy.drift.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * 模板工具类
 * 
 * @author zhy
 * 
 */
public class TemplateUtil {

    /**
     * 模板
     */
    public static Properties properties;

    /**
     * 城市列表
     */
    public static String[] cities;

    /**
     * 机器人话术
     */
    public static List<String> messages;

    /**
     * 缓存地址
     */
    public static String memcachedPath;

    static {
        init();
    }

    private synchronized static void init() {
        if (properties == null) {
            try {
                properties = new Properties();
                String classpath = Thread.currentThread().getContextClassLoader().getResource("").toString();
                classpath = classpath.substring(5) + "template.properties";
                properties.load(new InputStreamReader(new FileInputStream(classpath), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回模板信息
     * 
     * @param key
     * @param args
     * @return
     */
    public static String getTemplateMessage(String key, String... args) {
        String result = "";
        if (properties.containsKey(key)) {
            result = properties.getProperty(key);
        }
        if (args != null && args.length > 0) {
            return MessageFormat.format(result, args);
        }
        return result;
    }

    /**
     * 获取所有城市列表
     * 
     * @return
     */
    public static String[] getCities() {
        if (cities == null) {
            loadCities();
        }
        return cities;
    }

    /**
     * 加载城市列表
     */
    private synchronized static void loadCities() {
        try {
            InputStream is = TemplateUtil.class.getClassLoader().getResourceAsStream("location.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;
            List<String> locations = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                locations.add(line);
            }
            cities = new String[locations.size()];
            locations.toArray(cities);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static String getRobotMessage() {
        if (messages == null) {
            loadMessages();
        }
        return messages.get(getRandom(messages.size()));
    }

    public synchronized static int getRandom(int length) {
        return (int) (Math.random() * length);
    }

    /**
     * 加载机器人话术
     */
    private synchronized static void loadMessages() {
        try {
            InputStream is = TemplateUtil.class.getClassLoader().getResourceAsStream("message.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;
            List<String> msgs = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                msgs.add(line);
            }
            messages = msgs;
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
