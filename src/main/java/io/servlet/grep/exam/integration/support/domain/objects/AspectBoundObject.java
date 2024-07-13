package io.servlet.grep.exam.integration.support.domain.objects;

import io.servlet.grep.exam.integration.support.aspect.annotation.AfterBurn;
import io.servlet.grep.exam.integration.support.aspect.annotation.OriginIdentify;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AspectBoundObject {

    @OriginIdentify
    private String identifyNumeric;

    /**
     * @see io.servlet.grep.exam.integration.support.aspect.implement.ParseAndSetAspect
     * <span style="color:#4CC7CF;">
     * When If the field has annotation present @AfterBurn
     * The Aspect class will hook up it and cast a value of parsed from origin with @OriginIdentify.
     * </span>
     */
    @AfterBurn
    private String parsedIdentifyNumeric;
}
