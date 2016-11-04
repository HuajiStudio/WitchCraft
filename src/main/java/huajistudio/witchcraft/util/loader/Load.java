package huajistudio.witchcraft.util.loader;

import net.minecraftforge.fml.common.LoaderState;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Load {
	LoaderState value() default LoaderState.PREINITIALIZATION;
}
