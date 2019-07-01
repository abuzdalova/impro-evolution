package org.improevolution;

public interface MixingRule {
    /**
     * Mixes the part of the {@param source} array, starting at offset {@param sourceOffset}
     * and having length {@param length}, into the arrays for the left channel {@param targetL} and the right channel
     * {@param targetR}, starting at offset {@param targetOffset}.
     *
     * @param source the source array.
     * @param sourceOffset the offset of the data in the source array.
     * @param targetL the target array for the left channel.
     * @param targetR the target array for the right channel.
     * @param targetOffset the offset of the data in the target arrays.
     * @param length the length of the data, in samples.
     */
    void mix(double[] source, int sourceOffset, double[] targetL, double[] targetR, int targetOffset, int length);
}
