import java.lang.annotation.*;

/**
 * Created by burakdede on 15.10.15.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Request {
    String value();
}
