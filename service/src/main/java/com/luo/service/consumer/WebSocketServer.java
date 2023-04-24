package com.luo.service.consumer;

import com.alibaba.fastjson2.JSONObject;
import com.luo.service.consumer.utils.JwtAuthorization;
import com.luo.service.consumer.utils.game.GameMap;
import com.luo.service.mapper.UserMapper;
import com.luo.service.pojo.User;
import com.luo.service.utils.JWT.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    private static final ConcurrentHashMap<Integer, WebSocketServer> users=new ConcurrentHashMap<>();
    private static final CopyOnWriteArraySet<User> matchPool = new CopyOnWriteArraySet<>();
    private User user;
    private Session session=null;

    private static UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper){
        WebSocketServer.userMapper=userMapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session = session;
        //System.out.println("token: " + token);
        System.out.println("connect success");

        Integer userId = JwtAuthorization.getUserId(token);
        user = userMapper.selectById(userId);

        if(user!=null){
            users.put(user.getId(), this);
        }else{
            this.session.close();
        }

        System.out.println(users);
    }

    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("close success");
        if(user!=null){
            users.remove(user.getId());
            matchPool.remove(user);
        }
    }

    private void startMatching(){
        System.out.println("start matching");
        matchPool.add(user);

        while(matchPool.size()>=2){
            //前两个用户进行匹配
            Iterator<User> iterable=matchPool.iterator();
            User user1=iterable.next();
            User user2=iterable.next();

            //从匹配池中移除
            matchPool.remove(user1);
            matchPool.remove(user2);

            System.out.println("user1: "+user1);
            System.out.println("user2: "+user2);

            //生成游戏地图
            GameMap gameMap=new GameMap(13,13,20);

            //给用户1发送匹配成功消息
            JSONObject respA=new JSONObject();
            respA.put("event", "match-success");
            respA.put("opponent_username", user2.getUsername());
            respA.put("opponent_photo", user2.getPhoto());
            //发送游戏地图
            respA.put("map", gameMap.getMap());

            users.get(user1.getId()).sendMessage(respA.toJSONString());

            //给用户2发送匹配成功消息
            JSONObject respB=new JSONObject();
            respB.put("event","match-success");
            respB.put("opponent_username", user1.getUsername());
            respB.put("opponent_photo", user1.getPhoto());
            //发送游戏地图
            respB.put("map", gameMap.getMap());

            users.get(user2.getId()).sendMessage(respB.toJSONString());
        }
    }

    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void stopMatching(){
        System.out.println("stop matching");
        matchPool.remove(user);
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
        System.out.println("receive message: " + message);
        JSONObject data= JSONObject.parseObject(message);
        String event= data.getString("event");

        if("start-matching".equals(event)){
            startMatching();
        }else if("stop-matching".equals(event)){
            stopMatching();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
}

