package annotation;

import java.lang.annotation.*;

/**
 * Created by burakdede on 22.10.15.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Body {
}
