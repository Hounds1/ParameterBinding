package io.servlet.grep.exam.integration.support.domain.objects;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterceptionBoundObject {

    private String identifyNumeric;

    /**
     *<span style="color:#4CC7CF;">
     * Parsed Identify Numeric will be stored in this field.
     *</span>
     */
    private String parsedIdentifyNumeric;
}

