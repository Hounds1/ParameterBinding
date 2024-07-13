package io.servlet.grep.exam.integration.global.config;

import io.servlet.grep.exam.integration.global.interception.CustomInterceptor;
import io.servlet.grep.exam.integration.global.resolver.InterceptionArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CustomInterceptor customInterceptor;
    private final InterceptionArgumentResolver interceptionArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor)
                .excludePathPatterns("/integration/case/aspect");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(interceptionArgumentResolver);
    }
}
