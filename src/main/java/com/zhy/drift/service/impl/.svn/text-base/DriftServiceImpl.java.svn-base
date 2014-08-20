package com.zhy.drift.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.User;
import com.zhy.drift.cache.BaseMemCache;
import com.zhy.drift.service.DriftService;
import com.zhy.drift.service.MessageService;
import com.zhy.drift.service.UserService;
import com.zhy.drift.util.Extractor;
import com.zhy.drift.util.TemplateUtil;

/**
 * 主业务实现类<br>
 * 1. 实现发送漂流瓶 <br>
 * 2. 获取漂流瓶与其回复信息
 * 
 * @author zhy
 * 
 */
public class DriftServiceImpl extends AbstractService implements DriftService {

    public static Logger logger = Logger.getLogger(DriftServiceImpl.class);

    /**
     * 查看漂流瓶代码
     */
    public static String DRIFT_VIEW_CX = "cx";
    public static String DRIFT_VIEW_CK = "ck";

    /**
     * 查看更多
     */
    public static String DRIFT_M = "m";

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * 1. 发送漂流瓶 2. 获取随机漂流瓶 2. 获取回复信息
     */
    @Override
    public List<Message> sendAndGetBottle(Message message) {
        // 如果指定某人,需要将代号转换成phone
        // Specify a bottle
        List<Message> bottles = new ArrayList<Message>();
        if (message.getContent().contains("@")) {
            // @xxx:哈哈哈是到 @yyy:aasdsadad
            // String[] messages = splitByFormat2(message.getContent());

            // 抽取用户名
            Extractor extractor = new Extractor(message.getContent(), message.getContent());
            extractor.extract();
            loop:for(int i = 0; i < extractor.getUserNames().size() ; i++){
            	String name = extractor.getUserNames().get(i);
            	for(int j = name.length();j > 0;j--){
            		String nowName = name.substring(0, j);
            		User user = userService.getUserByName(nowName);
            		if(user != null){
            			extractor.addUser(nowName);
            			continue loop;
            		}
            		if(j == 1){
            			extractor.addUser(null);
            		}
            	}
            }
            for (int i = 0; i < extractor.getUsers().size(); i++) {
                
            	// 找到的昵称
            	String userRealName = extractor.getUsers().get(i);
            	
            	// 原要通知的昵称
                String noticeRawName = extractor.getUserNames().get(i);
                if (userRealName == null) {
                    message.setResult(getMessageResult(message)
                            + TemplateUtil.getTemplateMessage("drift.no.user", extractor.getUserNames().get(i)));
                } else {
                	User user = userService.getUserByName(userRealName);
                    // get last bottle id
                    Long lastBottleId = (Long) BaseMemCache.getBottleId(message.getFromUserId() + "_" + userRealName);
                    if (lastBottleId != null) {
                        message.setLastBottle(lastBottleId);
                    }
                    message.setToUserId(user.getId());
                    
                    // 要@昵称跟找到的昵称相同
                    if(userRealName.equals(noticeRawName)){
                    	message.setResult(getMessageResult(message)
                                + TemplateUtil.getTemplateMessage("drift.yes.user", userRealName));
                    }else{
                    	
                    	// 不同
                    	message.setResult(getMessageResult(message)
                                + TemplateUtil.getTemplateMessage("drift.yes.user.another", noticeRawName, userRealName));
                    }
                    // remove nick and another message
                    message.setContent(message.getContent().replace("@" + userRealName, ""));
                    // clone and add bottle
                    Message clone = new Message(message);
                    bottles.add(clone);
                }
            }
        } else {
            bottles.add(message);
            message.setResult(TemplateUtil.getTemplateMessage("drift.info.send"));
        }
        // get ramdon bottle
        getRandomBottle(message);
        getReplys(message);
        message.setResult(getMessageResult(message) + TemplateUtil.getTemplateMessage("drift.info.user.reply.tip"));
        addPostInfo(message);
        return bottles;
    }

    @Override
    public Message viewBottle(Message message) {
    	
    	// 判断今天是否可以捞瓶子
    	if(messageService.canSend(message.getFromUserId())){
	        getRandomBottle(message);
	        getReplys(message);
	
	        // 拼装回复话术
	        message.setResult(getMessageResult(message) + TemplateUtil.getTemplateMessage("drift.info.user.reply.tip"));
	        addPostInfo(message);
    	}else{
    		
    		// 不能捞了
    		message.setResult(TemplateUtil.getTemplateMessage("drift.max.get.bottles"));
    	}
        return message;
    }

    /**
     * 获取随机漂流瓶
     * 
     * @return
     */
    private Message getRandomBottle(Message message) {
        List<Message> randomBottles = messageService.getRandomBottle(message.getFromUserId());
        if (randomBottles == null || randomBottles.size() == 0) {
            message.setResult(getMessageResult(message) + TemplateUtil.getTemplateMessage("drift.no.bottle"));
        } else {
            message.setResult(getMessageResult(message)
                    + TemplateUtil.getTemplateMessage("drift.info.bottles", randomBottles.size() + ""));
            Long[] ids = new Long[randomBottles.size()];
            User[] users = new User[randomBottles.size()];
            for (int i = 0; i < randomBottles.size(); i++) {
                Message bottle = randomBottles.get(i);
                User user = userService.getUserById(bottle.getFromUserId());
                // A message for getting bottles.
                message.setResult(getMessageResult(message) + user.getName() + "的漂流信息:" + bottle.getContent() + "\n");
                ids[i] = bottle.getId();
                users[i] = user;
            }
            // save bottles id by memcached
            addBottleInMemcached(message.getFromUserId(), users, ids);
            // update received bottles
            messageService.updateReceivedBottles(ids);
        }
        return message;
    }

    /**
     * 获取回复信息
     * 
     * @return
     */
    private Message getReplys(Message message) {
        List<Message> replys = messageService.getBottlesByUserId(message.getFromUserId());
        if (replys == null || replys.size() == 0) {
            message.setResult(getMessageResult(message) + TemplateUtil.getTemplateMessage("drift.no.reply"));
        } else {
            appendResultAndUpdateBottles(message, replys);
        }
        return message;
    }

    /**
     * 拼装下行信息 <br>
     * 1. 拼装信息 <br>
     * 2. 修改已经发送的信息
     * 
     * @param message
     * @param bottles
     * @return
     */
    private Message appendResultAndUpdateBottles(Message message, List<Message> bottles) {
        if (message.getResult().length() < limitCount) {
            // record is sent bottle.
            List<Long> isReceivedBottles = new ArrayList<Long>();
            List<User> userLists = new ArrayList<User>();
            append(message, bottles, isReceivedBottles, userLists);
            Long ids[] = new Long[isReceivedBottles.size()];
            isReceivedBottles.toArray(ids);
            User[] users = new User[userLists.size()];
            userLists.toArray(users);
            messageService.updateReceivedBottles(ids);
            addBottleInMemcached(message.getFromUserId(), users, ids);
        }
        return message;
    }

    /**
     * 拼装结果
     * 
     * @param message
     * @param bottles
     * @param isReceivedBottles
     */
    private void append(Message message, List<Message> bottles, List<Long> isReceivedBottles, List<User> users) {
        for (Message bottle : bottles) {
            // Get user by phone
            User user = userService.getUserById(bottle.getFromUserId());
            String name = user.getName();
            users.add(user);
            String bottleMessage = bottle.getContent();
            // get last bottle message
            Message lastBottle = null;
            if (bottle.getLastBottle() != 0L) {
                lastBottle = messageService.getMessageById(bottle.getLastBottle());
            }
            String lastPartContent = null;
            if (lastBottle != null) {
                if (lastBottle.getContent().length() < 20) {
                    lastPartContent = lastBottle.getContent();
                } else {
                    lastPartContent = lastBottle.getContent().substring(0, 20) + "..";
                }
            }
            if (lastPartContent != null) {

                // 减去本次回复的内容长度
                if (limitCount
                        - lastPartContent.length()
                        - getMessageResultLength(message)
                        - TemplateUtil
                                .getTemplateMessage("drift.info.user.reply", lastPartContent, name, bottleMessage)
                                .length() > 0) {

                    // "It's wonderful day!"aaa回复您:yes!

                    message.setResult(message.getResult()
                            + TemplateUtil.getTemplateMessage("drift.info.user.reply", lastPartContent, name,
                                    bottleMessage));
                    isReceivedBottles.add(bottle.getId());
                }
            } else {
                if (limitCount
                        - getMessageResultLength(message)
                        - TemplateUtil.getTemplateMessage("drift.info.user.reply.not.length", name, bottleMessage)
                                .length() > 0) {
                    // "It's wonderful day!"aaa回复您:yes!
                    message.setResult(message.getResult()
                            + TemplateUtil.getTemplateMessage("drift.info.user.reply.not.length", name, bottleMessage));
                    isReceivedBottles.add(bottle.getId());
                }
            }
        }
    }

    /**
     * 将获取到的瓶子,放入缓存中
     * 
     * @param receivedPhone
     * @param users
     * @param ids
     */
    private void addBottleInMemcached(Long receivedUserId, User[] users, Long[] ids) {
        for (int i = 0; i < users.length; i++) {
            // add memcached
            BaseMemCache.setBottleId(receivedUserId + "_" + users[i].getName(), ids[i]);
            logger.info("[Memcached]: add key----" + receivedUserId + "_" + users[i].getName());
        }
    }

    /**
     * 根据固定格式切割<br>
     * example:@xxx:How are you?@aaa:Where are you from? <br>
     * complete: [0]:@xxx:How are you? [1]@yyy:Where are you from? <br>
     * 
     * @deprecated
     * @param content
     * @return
     */
    public String[] splitByFormat(String content) {
        int count = 0;
        List<String> messageList = new ArrayList<String>();
        for (int i = 0; content.indexOf("@", i) != -1 || content.substring(i).length() > 0;) {
            count++;
            // @xxx:How are you? only one.
            int lastIndex = i;
            i = content.indexOf("@", i);
            if (i == 0) {
                continue;
            }
            // @xxx:How are you?@yyy:I'm 13 years old.@zzz:Yes,all right.
            // @yyy:@------5 last:6-?
            if (lastIndex != 0) {
                lastIndex += 1;
            }
            messageList.add(content.substring(lastIndex, i));
        }
        if (count == 1) {
            messageList.add(content);
        }
        String[] messages = new String[messageList.size()];
        messageList.toArray(messages);
        return messages;
    }

    /**
     * 根据固定格式切割<br>
     * example:@xxx:How are you?@aaa:Where are you from? <br>
     * complete: [0]:@xxx:How are you? [1]@yyy:Where are you from? <br>
     * 
     * @param content
     * @return
     */
    public String[] splitByFormat2(String content) {
        String[] messages = content.split("@");
        List<String> resultList = new ArrayList<String>();
        for (int i = 0; i < messages.length; i++) {
            if (messages[i].length() <= 0) {
                continue;
            }
            resultList.add("@" + messages[i]);
        }
        String result[] = new String[resultList.size()];
        resultList.toArray(result);
        return result;
    }

    @Override
    public Message invoke(Message message) {
        return viewBottle(message);
    }

    @Override
    public boolean isTriggered(Message message) {
        if (message.getContent().equals(DRIFT_VIEW_CX) || message.getContent().equals(DRIFT_M)
                || message.getContent().equals(DRIFT_VIEW_CK)) {
            return true;
        }
        return false;
    }

}
