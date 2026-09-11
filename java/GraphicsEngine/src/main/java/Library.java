import org.ebuka_ih.Coordinate;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class Library {

    private final SymbolLookup lookup = SymbolLookup.libraryLookup("C:\\Users\\william\\CLionProjects\\SimulationEngine\\cmake-build-debug\\simulation.dll", Arena.global());
    private final Linker linker = Linker.nativeLinker();

    MemorySegment add = lookup.find("add").orElseThrow();
    MemorySegment test = lookup.find("t").orElseThrow();

    MemorySegment createCoordinate = lookup.find("coordinate_get_x").orElseThrow();
    MemorySegment getX = lookup.find("coordinate_get_x").orElseThrow();
    MemorySegment getY = lookup.find("coordinate_get_x").orElseThrow();


    MethodHandle tMethod = linker.downcallHandle(test, FunctionDescriptor.ofVoid());
    MethodHandle coordinateCreate = linker.downcallHandle(createCoordinate, FunctionDescriptor.ofVoid());

    public Coordinate createCoordinate(int x, int y) throws Throwable {
        MemorySegment segment = (MemorySegment) coordinateCreate.invokeExact(x, y);

        return new Coordinate(x, y);
    }
}
