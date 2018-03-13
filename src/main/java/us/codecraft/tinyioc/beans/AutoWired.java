package us.codecraft.tinyioc.beans;

/**
 * @author 邓凤麒
 * 创建时间：13/03/2018 16:42
 * 创建原因：
 **/

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//此处Autowired 只适用在字段上

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AutoWired {

}
