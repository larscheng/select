package com.slxy.www.webSocket;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengql
 * @description WebSocket类
 * @className MyWebSocket
 * @create 2018年03月20日  9:33
 */

//@ServerEndpoint("/webSocket/{userId}")    //注释掉可关闭websocket
public class MyWebSocket {


    private Logger logger = LoggerFactory.getLogger(getClass());
    // 日期格式化
    private static final SimpleDateFormat
            DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");



    @OnOpen
    public void onOpen(@PathParam("userId")String userId, Session session){
        logger.info("----------------------------------上线：userid："+userId);
        WebSocketUtil.put(userId,session);
    }


    @OnMessage
    public void onMessage(@PathParam("userId")String userId,String message){
        logger.info("----------------------------------发送消息");
        // 把客户端的消息解析为JSON对象
        JSONObject jsonObject = JSONObject.fromObject(message);
        // 获得昵称
         String nikeName = (String) jsonObject.get("nickname");
            Integer from = (Integer) jsonObject.get("from");
            Integer to = (Integer) jsonObject.get("to");
        //TODO 记录聊天记录


        //TODO 发送
        broadcast(jsonObject,from.toString(),to.toString());
        logger.info("----------------------------------发送完成");
    }


    @OnError
    public void onError(@PathParam("userId")String userId,Throwable t){
        logger.info("----------------------------------异常");
        WebSocketUtil.remove(userId);
    }

    @OnClose
    public void onClose(@PathParam("userId")String userId){
        logger.info("----------------------------------断开");
        WebSocketUtil.remove(userId);
    }

    private void broadcast(JSONObject message,String from, String to) {
        Session session = WebSocketUtil.get(to);
        Session mySession = WebSocketUtil.get(from);
        if (null!=session&&session.isOpen()){
            //发送到目的主机
            message.put("isSelf",true);
            message.put("date", DATE_FORMAT.format(new Date()));
            mySession.getAsyncRemote().sendText(message.toString());
            logger.info("----------------------------------在本机显示消息"+message.toString());

            message.put("isSelf",false);
            logger.info("----------------------------------送达目标主机");
            session.getAsyncRemote().sendText(message.toString());
            logger.info("----------------------------------在目标主机显示消息");
        }else {
            logger.info("----------------------------------目的主机不在线");
            Map<String,String> map = new HashMap<>();
            map.put("nickname","系统消息");
            map.put("content","<p>该用户暂时不在线！<br/></p>");
            JSONObject strMap = JSONObject.fromObject(map);
            strMap.put("date", DATE_FORMAT.format(new Date()));
            strMap.put("isSelf",true);
            logger.info("----------------------------------在本机显示消息"+strMap.toString());
            mySession.getAsyncRemote().sendText(strMap.toString());
        }

    }


}

