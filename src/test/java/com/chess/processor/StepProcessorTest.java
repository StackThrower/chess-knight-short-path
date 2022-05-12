package com.chess.processor;


import com.chess.processor.exception.InvalidStepProcessorParams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StepProcessorTest {

    @Test
    void nullableTestForStepProcessor() {

        assertThrows(InvalidStepProcessorParams.class, () -> StepProcessor.calculate(null, null, 0, null));

    }


}