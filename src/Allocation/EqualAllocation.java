package Allocation;
import Components.Process;
import Components.RAM;
import java.util.ArrayList;

public class EqualAllocation implements FrameAllocation{
    @Override
    public void allocateFrames(ArrayList<Process> processes, RAM ram) {
        int framesPerProcess = ram.getFrames().size() / processes.size();
        for(Process process: processes){
            process.setFrames(framesPerProcess);
        }
    }
}
