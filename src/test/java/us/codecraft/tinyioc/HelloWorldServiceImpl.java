package us.codecraft.tinyioc;

import lombok.Data;
import us.codecraft.tinyioc.beans.AutoWired;
import us.codecraft.tinyioc.message.ApplicationEventPublisher;
import us.codecraft.tinyioc.message.EventListener;
import us.codecraft.tinyioc.message.Message;

/**
 * @author yihua.huang@dianping.com
 */
@Data
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private OutputService outputService;

    @AutoWired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void helloWorld() {
        outputService.output(text);
    }

    @EventListener
    public void handleMessage(String msg) {
        System.out.println("Handle type String's message is:" + msg);
        applicationEventPublisher.publishEvent(new Message("through autowired publisher publish this message"));
    }
}
