package org.improevolution.generators;

import java.util.Arrays;

import org.improevolution.SoundGenerator;

/**
 * A simple generator that produces silence.
 *
 * @author Maxim Buzdalov
 */
public final class Silence implements SoundGenerator<Silence> {
    private Silence() {}

    @Override
    public void generate(double[] target, int offset, int length) {
        Arrays.fill(target, offset, length, 0.0);
    }

    private static final Silence INSTANCE = new Silence();

    public static Silence getInstance() {
        return INSTANCE;
    }
}
