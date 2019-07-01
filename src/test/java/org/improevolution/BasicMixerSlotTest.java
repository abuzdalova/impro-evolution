package org.improevolution;

import java.util.Arrays;

import org.improevolution.mixing.ConstantMixingRule;
import org.junit.Assert;
import org.junit.Test;

public class BasicMixerSlotTest {
    @Test
    public void sawAndPanning() {
        Mixer.Slot slot = new Mixer.Slot();
        slot.setGenerator(new SimpleSaw());
        slot.setMixingRule(new ConstantMixingRule(3.0, 1.0 / 3.0));
        int length = 1024;
        double[] l = new double[length];
        double[] r = new double[length];
        double[] temp = new double[length];
        slot.mix(temp, l, r, 0, length);
        for (int i = 0; i < length; ++i) {
            Assert.assertEquals(i % 5, l[i], 1e-9);
            Assert.assertEquals(2 * (i % 5), r[i], 1e-9);
        }
        slot.setMixingRule(ConstantMixingRule.IDENTITY_CENTER);
        Arrays.fill(l, 0.0);
        Arrays.fill(r, 0.0);
        slot.mix(temp, l, r, 0, length);
        for (int i = 0; i < length; ++i) {
            Assert.assertEquals(0.5 * (i % 5), l[i], 1e-9);
            Assert.assertEquals(0.5 * (i % 5), r[i], 1e-9);
        }
    }

    private static class SimpleSaw implements SoundGenerator<SimpleSaw> {
        @Override
        public void generate(double[] target, int offset, int length) {
            for (int i = 0; i < length; ++i) {
                target[i + offset] = i % 5;
            }
        }
    }
}
