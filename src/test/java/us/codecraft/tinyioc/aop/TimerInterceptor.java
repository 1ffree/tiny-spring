package us.codecraft.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author yihua.huang@dianping.com
 */
public class TimerInterceptor implements MethodInterceptor {

    //代理回调
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long time = System.nanoTime();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " start!");
		//执行真正的bean方法前

        //执行bean原始方法
		Object proceed = invocation.proceed();

		//执行真正的bean方法后
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time)
				+ " nanoseconds.");
		return proceed;
	}

}
