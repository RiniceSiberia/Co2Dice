package org.co2dice.mirai.plugin;

import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.co2dice.mirai.bean.game.ScenePool;

public class AnalysisUtils {

    public static void groupAnalysis(GroupMessageEvent e){
        Group group = e.getGroup();
        Member sender = e.getSender();
        ScenePool battlesPool = ScenePool.INSTANCE;
    }
    public static void friendAnalysis(FriendMessageEvent e){
        Friend friend = e.getSender();
        MessageChain msg = e.getMessage();
        String content = msg.contentToString();
    }
}
