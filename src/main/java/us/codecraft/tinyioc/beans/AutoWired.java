package us.codecraft.tinyioc.beans;

/**
 * @author winters
 * 创建时间：13/03/2018 16:42
 * 创建原因：
 **/

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//此处Autowired 只适用在字段注入 跟 构造注入

@Target({ElementType.FIELD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AutoWired {

    boolean required() default true;
}
