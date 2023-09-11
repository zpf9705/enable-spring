package top.osjf.assembly.cron.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import top.osjf.assembly.cron.AbstractCornRegister;
import top.osjf.assembly.cron.AbstractExceptionCronListener;
import top.osjf.assembly.cron.CronListener;

import java.lang.annotation.*;

/**
 * The annotation identifier of the timed task registration switch can specify the path for scanning {@link Cron}
 * and set the survival mode of the calling object {@link Type}.
 * <p>
 * This annotation will only take effect when it exists on the class header of the spring container object.
 * <p>
 * Please pay attention to how to use it
 * <p>
 *
 * @see CronTaskRegister
 * @see AbstractCornRegister
 * @see org.springframework.context.ApplicationContext
 * @see org.springframework.core.env.Environment
 * @author zpf
 * @since 1.1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CronTaskRegister.class})
public @interface EnableCronTaskRegister {

    /**
     * Carrying the path where the {@link Cron} class is located.
     * <p>
     * At the same time, task listeners can also be added to implement
     * {@link CronListener} or
     * {@link AbstractExceptionCronListener}
     * <p>
     * If it is null, the default is to use springboot to start the package path where the main class is located.
     *
     * @return alias for {{@link #basePackages()}}
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * His value shifts to {@link #value()}, consistent with it.
     * <p>
     * At the same time, task listeners can also be added to implement
     * {@link CronListener} or
     * {@link AbstractExceptionCronListener}
     * <p>
     * If it is null, the default is to use springboot to start the package path where the main class is located.
     *
     * @return alias for {{@link #value()}}
     */
    @AliasFor("value")
    String[] basePackages() default {};

    /**
     * The survival mode of the calling object triggered by a timed task,
     * which defaults to the proxy object of the spring container
     *
     * @return {@link Type}
     */
    Type type() default Type.PROXY;

    /**
     * Choose whether to start the timed thread pool even if no registration method is found.
     * <p>
     * But I won't actively add listeners for {@link CronListener},
     * unless a timing method with {@link Cron} annotation is added.
     *
     * @return if {@code true} , defaults to enabled
     * @since 3.2.2
     */
    boolean noMethodDefaultStart() default false;
}
