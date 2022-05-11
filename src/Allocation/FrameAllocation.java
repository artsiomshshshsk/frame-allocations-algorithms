package Allocation;
import Components.Process;
import Components.RAM;
import java.util.ArrayList;

public interface FrameAllocation {
    void allocateFrames(ArrayList<Process> processes, RAM ram);
}
