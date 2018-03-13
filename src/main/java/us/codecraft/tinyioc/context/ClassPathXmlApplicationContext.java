package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.BeanDefinition;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.beans.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.beans.io.ResourceLoader;
import us.codecraft.tinyioc.beans.xml.XmlBeanDefinitionReader;
import us.codecraft.tinyioc.message.MessageHandlerHolder;
import us.codecraft.tinyioc.message.MessageHandlerInvocation;

import java.util.Map;

/**
 * @author yihua.huang@dianping.com
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    private MessageHandlerHolder messageHandlerHolder = new MessageHandlerHolder();

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
        //将自身注入进bean容器
        registerSelf();
        //消息处理者handler 扫描所有的bean 对方法上有EventListener注解的
        beanFactory.addBeanPostProcessor(messageHandlerHolder);
    }

    private void registerSelf() throws Exception {
        BeanDefinition self = new BeanDefinition();
        self.setBeanClassName(this.getClass().getName());
        self.setBeanClass(this.getClass());
        self.setBean(this);
        beanFactory.registerBeanDefinition("applicationContext", self);
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
