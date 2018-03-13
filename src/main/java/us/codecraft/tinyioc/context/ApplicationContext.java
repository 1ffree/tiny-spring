package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.factory.BeanFactory;
import us.codecraft.tinyioc.message.ApplicationEventPublisher;

/**
 * @author yihua.huang@dianping.com
 */
public interface ApplicationContext extends BeanFactory, ApplicationEventPublisher{
}
