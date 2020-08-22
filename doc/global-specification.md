# Global Specification for the Impro-Evolution Project

This is an attempt to sketch what we want Impro-Evolution to be.

## Impro-Evolution: What It Is

Impro-Evolution is intended to be a tool for (possibly collaborative) improvisation.
The music played by the instruments, as well as the instruments themselves,
emerge during the performance in a process resembling natural evolution (and, more specifically, evolutionary algorithms).
The instruments together with their parameters are *individuals* of this evolution,
they undergo changes, such as *mutation*, and recombination, or *crossover*.
The human performer that stands behind each instrument chooses the individuals that continue to live and sound.

From a philosophical viewpoint, the role of a performer switches from an active creator that directly produces the sound
to an *advanced listener* that can only influence the development direction of the sound, while still being creative.
One can also see a performer as a conductor of a mad orchestra, or a player on a furious and hardly controllable instrument
which deserves to be called "the force of nature".

Contrary to most classic instruments, the time scale of Impro-Evolution is not milliseconds, but rather seconds.
This facilitates creation of slowly developing music, the traits shared by ambient and techno, but the produced sounds
may vary from slow soundscapes to avant-garde noise, depending on the particular structure of the individual chosen by the performers.

With this time scale, and also with the music that is purely synthesized,
Impro-Evolution also offers opportunities that are hard to meet by other means:

* Real-time distributed improvisations in true sound without requiring a high-speed and low-latency internet connection:
  the software used by performers (and maybe even listeners!) exchanges only small pieces of information a few times per second.
* The whole piece can be stored as its "source code", that is a sequence of individuals and events.
  Just like MIDI or other means to store scores, it takes a negligible amount of space compared to the corresponding waveform in any format,
  but allows a lot more diversity in what can be encoded.
* If performers wish so, the choices offered to them may also be stored in the event sequence,
  so that any listener can subsequently listen to the decisions of performers during the whole performance.
* Anyone can continue the piece, starting from an arbitrary point, maybe to a completely different direction,
  which somewhat resembles alternative endings in computer games.

## Related Work

## Instruments as Individuals

Impro-Evolution intends to use *waveform generators* as instruments.

For terminology reasons, we need to say first that there is a distinction between the *principle* of a particular waveform generator,
which roughly corresponds to the genus of the individuals, and the *instances* of these generators, which are the individuals.
Below, we use the word *instrument* to denote the former (which, in the Java world, would correspond to a particular class implementing that principle),
and the word *individual* to denote the latter.

The project will eventually contain lots of different instruments available.
The performer is generally required to choose one instrument to use throughout the whole piece (so that evolution is in fact possible).
During the performance, a large number of individuals belonging to that instrument appear on the performer's instance of Impro-Evolution,
and the selected ones will be actually played to the public.

### Designing Instruments

There are quite few restrictions on the principle an instrument can be based on. The most important ones are probably:

- *Locality with high probability*: many small changes in the individual result in small changes in the sound that it produces, so that not every possible mutation introduces a complete surprise.
- *Unboundedness*: the instrument should allow an infinite number of different individuals.

However, the first one is not really formal, whereas the second one may be in fact replaced by a weaker requirement of allowing just "too many" different individuals.

We expect that most of the instruments will internally be similar to modular synthesizers with both real-valued controls (the equivalents of knobs) and discrete controls (the equivalents of connections).
A performer is not required to know how the chosen instrument works, since one does not "play" them but instead chooses between them based on how they sound.

### Instrument Versioning

A crucial part of the ecosystem is that once an instrument is released to public, it shall remain available for the rest of the life unchanged
(with the exception of bug fixing and refactoring). That is, any subsequent version of Impro-Evolution must produce the sound that is indistinguishable
from the version that introduced that instrument, for artistic backward compatibility reasons. This is necessary so that any subsequent version will be
able to play the existing performance records verbatim as they were created.

For this reason, the class names of the instruments, apart from the trivial ones, are to be interpreted as an integral part of the instrument's description.
They are expected to contain the unique author identification, as well as either the version information
(since they must be able to co-exist with their possible subsequent revisions) or the creation date.

## Structure of the Performance

TODO: mixing of an instrument in the performance - chosen by the performer, not evolvable.
TODO: cross-fade between the previous and the next individual from the same performer - also chosen by the performer and not evolvable.
TODO: instrument events, mix change events, what else?

## Performers, Listeners, Computers

Normally, each instance of Impro-Evolution runs on its own computer (typically a laptop or a desktop, however, a reasonably powerful smartphone can do it as well).
Each performer uses their own instance, as the device's screen and audio facilities are exclusively used by the performer.

A dedicated central instance is allocated where the actual performance is being played.
This instance translates the sound to the sound system (in the case of onsite events), or to a streaming service (in the case of online events), or both.

Most online listeners will typically connect to the streaming service and listen the sound synthesized by the central instance.
However, they can run their instances instead, which connect to the central instance, receive the events from it and synthesize the sound on their own.
