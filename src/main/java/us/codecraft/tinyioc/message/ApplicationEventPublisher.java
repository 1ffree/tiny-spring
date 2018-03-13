package us.codecraft.tinyioc.message;

/**
 * @author 邓凤麒
 * 创建时间：13/03/2018 15:47
 * 创建原因：
 **/
public interface ApplicationEventPublisher {
    /**
     * 发布消息
     * @param object
     */
    void publishEvent(Object object);
}
