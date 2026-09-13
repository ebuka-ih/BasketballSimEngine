package org.ebuka_ih;


import org.ebuka_ih.Generation.StatFactory;
import org.ebuka_ih.Player.Stats;
import org.ebuka_ih.en.Position;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class Main {
    static void main() throws Throwable {
        Linker linker = Linker.nativeLinker();


        SymbolLookup lookup = SymbolLookup.libraryLookup("C:\\Users\\william\\Documents\\repos\\BasketballSimEngine\\cpp\\SimulationEngine\\cmake-build-debug\\simulation.dll", Arena.global());

        MemorySegment importStat = lookup.find("exportStat").orElseThrow();
        MethodHandle tMethod = linker.downcallHandle(importStat, FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
        Stats stat = StatFactory.build(Position.PG);
        int[] stats = stat.ship();
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nativeStats =
                    arena.allocate(ValueLayout.JAVA_INT, stats.length);

            nativeStats.asSlice(0, (long) stats.length * ValueLayout.JAVA_INT.byteSize())
                    .copyFrom(MemorySegment.ofArray(stats));

            MemorySegment result =
                    (MemorySegment) tMethod.invokeExact(nativeStats);

            // result is the native Stats* returned by C++
        }
    }
}
