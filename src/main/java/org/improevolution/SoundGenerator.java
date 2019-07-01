package org.improevolution;

/**
 * This is a basic interface for sound generator.
 *
 * A sound stream is an abstraction for any thing which can generate sound.
 * The interface is parameterized with an exact type of the implementing class,
 * so that generic procedures of mutation and crossover, which have to be compatible with
 * the particular implementation type, can be expressed.
 *
 * @param <Derived> the exact type of the implementing class.
 * @author Maxim Buzdalov
 */
public interface SoundGenerator<Derived extends SoundGenerator<Derived>> {
    /**
     * Generates {@param length} samples of sound and
     * places them into the {@param target} array starting at {@param offset}.
     *
     * The generator should assume that the indices are always valid,
     * e.g. both {@code target[offset]} and {@code target[offset + length - 1]} will never produce exceptions,
     * and {@param length} will be non-negative.
     *
     * The samples should be simply put into the array, the existing values should be discarded.
     * Exactly {@param length} samples should be generated. For instance, if a generator wants to produce silence,
     * it shall write zero values onto the corresponding positions.
     *
     * @param target the array to write the samples to.
     * @param offset the index where the first generated sample should be written.
     * @param length the number of samples to be written.
     */
    void generate(double[] target, int offset, int length);
}
