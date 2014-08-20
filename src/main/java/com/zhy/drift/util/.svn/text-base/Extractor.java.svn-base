package com.zhy.drift.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.zhy.drift.bean.User;

/**
 * 抽取@可能的用户昵称
 * 
 * @author zhy19890221@gmail.com
 * 
 */
public class Extractor {

    /**
     * 源内容
     */
    private String rawContent;

    private String content;

    /**
     * 抽取名称(未验证)
     */
    private List<String> userNames = new ArrayList<String>();

    /**
     * 抽取用户(已验证)
     */
    private List<String> users = new ArrayList<String>();

    public Extractor(String rawContent, String content) {
        this.rawContent = rawContent;
        this.content = content;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public String getRawContent() {
        return rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
    
    public void addUser(String userName){
    	users.add(userName);
    }

    /**
     * 抽取@用户
     * 
     * @param content
     * @return
     */
    public List<String> extract() {
        if (content.contains("@")) {
            String newContent = content.substring(content.indexOf("@") + 1);
            if (newContent.contains("@")) {
                Extractor extractor = new Extractor(rawContent, newContent);
                userNames.addAll(extractor.extract());
            } else {
                addContent(userNames, newContent);
            }

            // 如果源内容不等于剩下的内容,增加该昵称 例raw aaa@bbbb@ccc content:bbb@ccc 添加bbb
            if (!content.equals(rawContent)) {
                addContent(userNames, content.substring(0, content.indexOf("@")));
            }
        }
        Collections.sort(userNames, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                if (o1.length() >= o2.length()) {
                    return 1;
                }
                return 0;
            }

        });
        return userNames;
    }

    private void addContent(List<String> args, String newContent) {
        if (newContent.length() == 0) {
            return;
        }
        int nearest = 0;
        int space = newContent.indexOf(" ");
        int comma = newContent.indexOf(",");
        int commaCn = newContent.indexOf("，");
        int period = newContent.indexOf(".");
        int periodCn = newContent.indexOf("。");
        int mao = newContent.indexOf(":");
        int maoCn = newContent.indexOf("：");
        int plus = newContent.indexOf("+");
        int sub = newContent.indexOf("-");
        nearest = max(new int[] { space, comma, commaCn, period, periodCn, mao, maoCn, plus, sub });
        if (nearest == -1) {
            nearest = newContent.length();
        }
        String nick = newContent.substring(0, nearest);
        args.add(nick);
    }

    private static int max(int[] is) {
        int max = 0;
        for (int i = 0; i < is.length - 1; i++) {
            if (i == 0) {
                max = is[i];
            }
            if (is[i] < is[i + 1]) {
                max = is[i + 1];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String rowContent = "@大好:哈哈，你也好";
        Extractor extractor = new Extractor(rowContent, rowContent);
        extractor.extract();
        System.out.println(rowContent);
        for (String str : extractor.getUserNames()) {
            System.out.println(str);
        }
    }

}
