package io.servlet.grep.exam.integration.support.aspect.implement;

import io.servlet.grep.exam.integration.support.aspect.annotation.AfterBurn;
import io.servlet.grep.exam.integration.support.aspect.annotation.OriginIdentify;
import io.servlet.grep.exam.integration.support.domain.objects.AspectBoundObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

@Aspect
@Component
public class ParseAndSetAspect {

    @Before("@annotation(io.servlet.grep.exam.integration.support.aspect.annotation.HookUp)")
    public void hookUp(JoinPoint joinPoint) throws Exception {
        System.out.println("hookUp method called");

        String originIdentify = "";
        AspectBoundObject originObject = null;

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof AspectBoundObject) {
                AspectBoundObject object = (AspectBoundObject) arg;
                originObject = object;

                Field[] declaredFields = object.getClass().getDeclaredFields();

                for (Field declaredField : declaredFields) {
                    if (declaredField.isAnnotationPresent(OriginIdentify.class)) {

                        if (!declaredField.canAccess(object)) {
                            declaredField.setAccessible(true);
                        }

                        originIdentify = (String) declaredField.get(object);

                        break;
                    }
                }
            }
        }

        if (originObject != null && StringUtils.hasText(originIdentify)) {
            convertAndSet(originObject, originIdentify);
        } else {
            throw new RuntimeException("Cannot cast parsed identify.");
        }
    }

    /**
     * @param object
     * <span style="color:#4CC7CF;">
     * The parameter object in which the parsed value will be stored.
     * </span>
     * @param target
     * <span style="color:#4CC7CF;">
     * The original identifier to be parsed.
     * </span>
     * <br>
     * <br>
     * <span style="color:#4CC7CF;">
     * Parses the target and stores the result in the object.
     * </span>
     */

    private void convertAndSet(AspectBoundObject object, String target) throws IllegalAccessException {

        // Proceed parse in here before cast
        String parsedIdentify = "parsed example";

        Field[] declaredFields = object.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(AfterBurn.class)) {

                if (!declaredField.canAccess(object)) {
                    declaredField.setAccessible(true);
                }

                declaredField.set(object, parsedIdentify);

                break;
            }
        }
    }
}
