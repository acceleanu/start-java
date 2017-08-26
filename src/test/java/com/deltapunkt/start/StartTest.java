package com.deltapunkt.start;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StartTest {
    @Test
    public void exampleTest() {
        Start start = new Start();
        assertThat(start.start()).isEqualTo(96);
    }
}
