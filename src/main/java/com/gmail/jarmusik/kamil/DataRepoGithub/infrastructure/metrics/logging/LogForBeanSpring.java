package com.gmail.jarmusik.kamil.DataRepoGithub.infrastructure.metrics.logging;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogForBeanSpring {
}
