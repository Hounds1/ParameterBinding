package io.servlet.grep.exam.integration.global.resolver;

import io.servlet.grep.exam.integration.global.interception.CustomInterceptor;
import io.servlet.grep.exam.integration.support.domain.objects.InterceptionBoundObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <span style="color:#4CC7CF;">
 * The ArgumentResolver will check the type of your request's attributes.<br>
 * If the attribute type is within the handling range of this resolver,<br>
 * then this class will support mapping it in your controller.
 * </span>
 */

@Component
public class InterceptionArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(InterceptionBoundObject.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return ((HttpServletRequest) webRequest.getNativeRequest()).getAttribute(CustomInterceptor.INTERCEPTION_OBJECT_KEY);
    }
}
