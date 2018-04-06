package us.codecraft.tinyioc.aop;

import us.codecraft.tinyioc.beans.factory.BeanFactory;

/**
 * @author yihua.huang@dianping.com
 */

/*
    此接口主要的目的，是将BeanFactory的引用 注入进一些特殊的bean中，例如 BeanProcessor的实现
    AspectJAwareAdvisorAutoProxyCreator 需要处理一些AOP相关的代理
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
