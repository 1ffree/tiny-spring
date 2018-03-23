package us.codecraft.tinyioc.message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author winters
 * 创建时间：13/03/2018 15:57
 * 创建原因：
 **/
public class MessageHandlerInvocation {

    private Method method;

    public Parameter getParameterType() {
        return parameterType;
    }

    private Parameter parameterType;

    private Object target;

    public MessageHandlerInvocation(Method method, Parameter parameter, Object target) {
        this.method = method;
        this.parameterType = parameter;
        this.target = target;
    }

    public void handleMessage(Object object) throws InvocationTargetException, IllegalAccessException {
        method.invoke(target, object);
    }
}
