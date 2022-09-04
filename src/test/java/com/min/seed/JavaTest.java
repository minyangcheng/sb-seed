package com.min.seed;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JavaTest {

    @Test
    public void test() {
        Object b = 1;
        log.info(b.getClass().getName()+"");
    }
}
