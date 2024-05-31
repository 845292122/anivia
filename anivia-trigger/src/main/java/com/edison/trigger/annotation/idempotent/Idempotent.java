package com.edison.trigger.annotation.idempotent;

import java.lang.annotation.*;

/**
 * 幂等
 * 
 * @author edison
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {

  /**
   * 提示消息
   * 
   * @return
   */
  public String message() default "请勿重复提交";

}
