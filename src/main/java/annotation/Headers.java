package annotation;

import java.lang.annotation.*;

/**
 * Created by burakdede on 23.10.15.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Headers {
    Header[] value();
}
