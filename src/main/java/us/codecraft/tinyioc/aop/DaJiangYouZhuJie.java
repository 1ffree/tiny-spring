package us.codecraft.tinyioc.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//打酱油注解
@Documented
//保证这个注解能留到运行时环境
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DaJiangYouZhuJie {
    String value();
}
