package annotation.test1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author li
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface  FruitColor {

    enum Color{
        /**
         * 红色
         *
         */
        RED,
        /**
         *
         *黄色
         *
         */
        YELLOW,
        /**
         *白色
         *
         */
        WHITE
    }

    Color fruitColor() default Color.RED;

}
