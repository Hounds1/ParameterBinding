package io.servlet.grep.exam.integration.global.interception;

import io.servlet.grep.exam.integration.support.domain.objects.InterceptionBoundObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    public static final String INTERCEPTION_OBJECT_KEY = "intercepted";

    /**
     * <pre>
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return boolean indicating whether the execution chain should proceed with the next interceptor or the handler itself
     * @throws Exception in case of any errors
     * <br>
     * <br>
     * <span style="color:#4CC7CF;">
     * A new attribute will be bound here when a request is detected.
     * </span>
     * </pre>
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre Handle method is Calling: " + request.getRequestURI());

        String encryptedIdentify = request.getParameter("identifyNumeric");

        String decrypted = decrypt(encryptedIdentify);

        InterceptionBoundObject interceptionBoundObject = InterceptionBoundObject.builder()
                .identifyNumeric(encryptedIdentify)
                .parsedIdentifyNumeric(decrypted)
                .build();

        request.setAttribute(INTERCEPTION_OBJECT_KEY, interceptionBoundObject);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        System.out.println("Request and Response is completed");
    }

    private String decrypt(String encrypted) {
        return encrypted + "decryptedValue";
    }
}
