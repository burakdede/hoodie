package annotation;

import java.lang.annotation.*;

/**
 * Created by burakdede on 20.10.15.
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PathParam {
    String value();
}
