package com.slxy.www.webSocket;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhengql
 * @description 聊天工具类
 * @className WebSocketUtil
 * @create 2018年03月20日  9:18
 */
public class WebSocketUtil {

    private static Map<String,Session> map = new ConcurrentHashMap<>();
    private static final String PREFIX = "mws";

    private static String getKey(String userId){
//        return PREFIX+"_"+userId;
        return userId;
    }



    public static void put(String userId,Session session){
        map.put(getKey(userId),session);
    }

    public static Session get(String userId){
        return map.get(getKey(userId));
    }

    /**
     * 群发获取Session
     */
    public static List<Session> getOtherSession(String userId){
        List<Session> sessions = new ArrayList<>();
        Set<Map.Entry<String,Session>> set = map.entrySet();
        for (Map.Entry<String,Session> s:set){
            if (s.getKey().equals(getKey(userId))){
                sessions.add(s.getValue());
            }
        }
        return sessions;
    }


    public static void remove(String userId){
        map.remove(getKey(userId));
    }


}

