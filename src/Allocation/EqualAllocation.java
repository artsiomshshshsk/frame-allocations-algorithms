package Allocation;
import Components.Process;
import Components.RAM;
import java.util.ArrayList;

public class EqualAllocation implements FrameAllocation{

    private boolean executed = false;

    @Override
    public void allocateFrames(ArrayList<Process> processes, RAM ram) {
        if(!executed){
            int framesPerProcess = ram.getFrames().size() / processes.size();

            for(Process process: processes){
                process.setFrames(framesPerProcess);
            }

            System.out.println("Allocated "+ framesPerProcess * processes.size() +" frames from " + ram.getFrames().size());
            executed = true;
        }
    }
}
