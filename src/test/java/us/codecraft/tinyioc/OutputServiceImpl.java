package us.codecraft.tinyioc;

import lombok.Data;
import us.codecraft.tinyioc.message.EventListener;
import us.codecraft.tinyioc.message.Message;

/**
 * @author yihua.huang@dianping.com
 */
@Data
public class OutputServiceImpl implements OutputService {

    HelloWorldService helloWorldService;

    @Override
    public void output(String text){
        System.out.println(text);
    }

    @EventListener
    public void handleMessage(Message message){
        System.out.println("Handle message'type is Message msg is: " + message.getMsg());
    }

}
