package org.improevolution.generators;

import org.junit.Assert;
import org.junit.Test;

public class SilenceTest {
    @Test
    public void fillsWithZeros() {
        double[] array = new double[1024];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i + 1;
        }
        Silence.getInstance().generate(array, 0, 1024);
        for (double v : array) {
            Assert.assertEquals(0, v, 1e-12);
        }
    }
}
