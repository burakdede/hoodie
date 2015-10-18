package annotation;

import java.lang.annotation.*;

/**
 * Created by burakdede on 15.10.15.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface QueryParam {
    String value();
}
