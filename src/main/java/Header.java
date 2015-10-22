import java.lang.annotation.*;

/**
 * Created by burakdede on 18.10.15.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = Headers.class)
public @interface Header {
    String value();
}
