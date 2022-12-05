package org.co2dice.mirai.plugin;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NewFriendRequestEvent;
import org.co2dice.mirai.bean.dice.DiceList;
import org.co2dice.mirai.bean.dice.NormalDice;

import java.util.List;


/**
 * 使用 Java 请把
 * {@code /src/main/resources/META-INF.services/net.mamoe.mirai.console.plugin.jvm.JvmPlugin}
 * 文件内容改成 {@code org.example.mirai.plugin.JavaPluginMain} <br/>
 * 也就是当前主类全类名
 *
 * 使用 Java 可以把 kotlin 源集删除且不会对项目有影响
 *
 * 在 {@code settings.gradle.kts} 里改构建的插件名称、依赖库和插件版本
 *
 * 在该示例下的 {@link JvmPluginDescription} 修改插件名称，id 和版本等
 *
 * 可以使用 {@code src/test/kotlin/RunMirai.kt} 在 IDE 里直接调试，
 * 不用复制到 mirai-console-loader 或其他启动器中调试
 */

public final class JavaPluginMain extends JavaPlugin {
    public static final JavaPluginMain INSTANCE = new JavaPluginMain();
    private JavaPluginMain() {
        super(new JvmPluginDescriptionBuilder("org.co2dice.mirai-example", "0.1.0")
                .info("这是一个牧序的测试插件，用来测试他的新式跑团规则.")
                .name("混乱或秩序规则专用骰")
                .author("牧序")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded");
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        eventChannel.subscribeAlways(GroupMessageEvent.class,m -> {
            String content = m.getMessage().contentToString();
           if (content.startsWith(".")){
               if (content.startsWith(".r")){
                   m.getGroup().sendMessage(String.valueOf(new DiceList(new NormalDice(100)).roll()));
               }
           }
        });


        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            getLogger().info(g.getMessage().contentToString());

        });
        eventChannel.subscribeAlways(FriendMessageEvent.class, f -> {
            //监听好友消息
            getLogger().info(f.getMessage().contentToString());
        });
        eventChannel.subscribeAlways(NewFriendRequestEvent.class, NewFriendRequestEvent::accept);
        //自动同意好友申请
        eventChannel.subscribeAlways(BotInvitedJoinGroupRequestEvent.class,BotInvitedJoinGroupRequestEvent::accept);
        //自动同意加群申请

    }
}
