package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.BeanDefinition;
import us.codecraft.tinyioc.beans.BeanPostProcessor;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.message.MessageHandlerHolder;
import us.codecraft.tinyioc.message.MessageHandlerInvocation;

import java.util.List;

/**
 * @author yihua.huang@dianping.com
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    private MessageHandlerHolder messageHandlerHolder = new MessageHandlerHolder();

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        //消息处理者handler 扫描所有的bean 对方法上有EventListener注解的
        beanFactory.addBeanPostProcessor(messageHandlerHolder);
        //将自身注入进bean容器
        registerSelf();
        onRefresh();
    }

    private void registerSelf() throws Exception {
        BeanDefinition self = new BeanDefinition();
        self.setBeanClassName(this.getClass().getName());
        self.setBeanClass(this.getClass());
        self.setBean(this);
        beanFactory.registerBeanDefinition("applicationContext", self);
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    protected void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }


    @Override
    public void publishEvent(Object object) {
        for (MessageHandlerInvocation messageHandlerInvocation : messageHandlerHolder.getMessageHandlers()) {
            if (messageHandlerInvocation.getParameterType().getType().isInstance(object)) {
                try {
                    messageHandlerInvocation.handleMessage(object);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
