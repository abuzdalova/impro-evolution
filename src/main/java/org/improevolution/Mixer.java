package org.improevolution;

/**
 * An interface for a mixer, which is used to produce sound from a number of sound generators.
 *
 * Every mixer is active since it is created. To be stopped, it should be closed using the {@link Mixer#close()} method.
 *
 * @author Maxim Buzdalov
 */
public interface Mixer extends AutoCloseable {
    /**
     * This class represents a slot which contains a generator along with its mixing rule. 
     * The physical meaning of a slot is a place for a separate individual, which is evaluated by the user. 
     * Typically, there is a small number of slots, e.g. left, middle and right.
     * 
     * @author Maxim Buzdalov
     */
    final class Slot {
        private SoundGenerator<?> generator;
        private MixingRule mixingRule;

        /**
         * Returns the generator which is currently set in this slot.
         * @return the generator.
         */
        public SoundGenerator<?> getGenerator() {
            return generator;
        }

        /**
         * Sets the generator to be used in this slot.
         * @param generator the new generator.
         */
        public void setGenerator(SoundGenerator<?> generator) {
            this.generator = generator;
        }

        /**
         * Returns the mixing rule which is currently set in this slot.
         * @return the mixing rule.
         */
        public MixingRule getMixingRule() {
            return mixingRule;
        }

        /**
         * Sets the mixing rule to be used in this slot.
         * @param mixingRule the new mixing rule.
         */
        public void setMixingRule(MixingRule mixingRule) {
            this.mixingRule = mixingRule;
        }

        protected final void mix(double[] tempArray, double[] targetL, double[] targetR, int targetOffset, int length) {
            if (generator != null) {
                generator.generate(tempArray, 0, length);
                if (mixingRule != null) {
                    mixingRule.mix(tempArray, 0, targetL, targetR, targetOffset, length);
                }
            }
        }
    }

    /**
     * Creates a new slot and returns it.
     * @return the newly created slot.
     */
    Slot createSlot();

    /**
     * Removes the given slot.
     * @param theSlot the slot to be removed.
     */
    void removeSlot(Slot theSlot);
}
