package io.servlet.grep.exam.integration.support.controller;

import io.servlet.grep.exam.integration.support.aspect.annotation.HookUp;
import io.servlet.grep.exam.integration.support.domain.objects.AspectBoundObject;
import io.servlet.grep.exam.integration.support.domain.objects.InterceptionBoundObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/integration/case")
public class InterceptionBindController {

    @PostMapping("/interception")
    @ResponseBody
    public ResponseEntity<String> interception(InterceptionBoundObject intercepted) {
        if (intercepted.getParsedIdentifyNumeric() == null) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("failed");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(intercepted.getParsedIdentifyNumeric());
        }
    }

    @PostMapping("/aspect")
    @HookUp
    @ResponseBody
    public ResponseEntity<String> aspect(@RequestBody AspectBoundObject bound) {
        if (bound.getParsedIdentifyNumeric() == null) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("failed");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(bound.getParsedIdentifyNumeric());
        }
    }
}
