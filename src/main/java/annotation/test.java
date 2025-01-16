package annotation;


import java.lang.annotation.*;

    /**
     * @author lizhiqiang
     */
    @Documented
    @Inherited
    @Target({ ElementType.FIELD, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface test

    {
       // RetentionPolicy cas();
         String value() default "";
    }



