package us.codecraft.tinyioc.message;

import us.codecraft.tinyioc.beans.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 邓凤麒
 * 创建时间：13/03/2018 15:51
 * 创建原因：
 **/
public class MessageHandlerHolder implements BeanPostProcessor {

    private List<MessageHandlerInvocation> messageHandlers = new ArrayList<>();

    public List<MessageHandlerInvocation> getMessageHandlers() {
        return messageHandlers;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        for (Method method : bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventListener.class)) {
                if (method.getParameters().length == 1) {
                    MessageHandlerInvocation messageHandlerInvocation = new MessageHandlerInvocation(
                            method,
                            method.getParameters()[0], bean);
                    messageHandlers.add(messageHandlerInvocation);
                }
            }
        }
        return bean;
    }

}
