package org.improevolution.mixing;

import org.improevolution.MixingRule;

/**
 * This is a simple mixing rule which keeps fixed both the volume and the balance.
 *
 * The <b>volume</b> is a simple multiple applied to the input. It can be an arbitrary value, although NaNs,
 * infinities and numbers much greater than one by the absolute value are discouraged.
 * Negative values invert the phase.
 *
 * The <b>balance</b> tells how the signal is distributed between the left and the right channels.
 * More precisely, the multiple of {@code 0.5 * (1 - balance)} is applied to the signal which targets the left channel,
 * and the multiple of {@code 0.5 * (1 + balance)} is applied to the signal which targets the right channel.
 * This means that {@code balance == 0} centers the signal between the channels, while {@code balance == -1} sends
 * it to the left channel exclusively, and {@code balance == 1} sends it to the right channel exclusively.
 *
 * Again, balance can be any number, and some tricky combinations with out-of-range balance values are possible.
 *
 * @author Maxim Buzdalov
 */
public class ConstantMixingRule implements MixingRule {
    private final double volume;
    private final double balance;

    /**
     * Creates the constant mixing rule with the specified values for volume and balance.
     *
     * For the description of what volume and balance are, please refer to the class-wide javadoc
     * for {@link ConstantMixingRule}.
     *
     * @param volume the volume.
     * @param balance the balance.
     */
    public ConstantMixingRule(double volume, double balance) {
        this.volume = volume;
        this.balance = balance;
    }

    @Override
    public void mix(double[] source, int sourceOffset, double[] targetL, double[] targetR, int targetOffset, int length) {
        double leftMul = volume * 0.5 * (1 - balance);
        double rightMul = volume * 0.5 * (balance + 1);
        for (int i = 0; i < length; ++i, ++sourceOffset, ++targetOffset) {
            double v = source[sourceOffset];
            targetL[targetOffset] += v * leftMul;
            targetR[targetOffset] += v * rightMul;
        }
    }

    /**
     * The very simple mixing rule which centers the signal and retains its original volume.
     */
    public static final ConstantMixingRule IDENTITY_CENTER = new ConstantMixingRule(1, 0);
}
