package Allocation;

import Components.Process;
import Components.RAM;

import java.util.ArrayList;

public class ProportionalAllocation implements FrameAllocation{
    private boolean wasExecuted = false;

    @Override
    public void allocateFrames(ArrayList<Process> processes, RAM ram) {
        if(!wasExecuted){
            int totalAmountOfPages = 0;
            int RAMSize = ram.getFrames().size();

            for(Process process: processes){
                totalAmountOfPages += process.getLogicalMemory().size();
            }

            int splitedFrames = 0;

            for(Process process: processes){
                int frames = (int)((double)process.getLogicalMemory().size() / (double) totalAmountOfPages * RAMSize);
                process.setFrames(frames);
                splitedFrames += frames;
            }

            System.out.println("Allocated "+ splitedFrames +" frames from " + ram.getFrames().size());
            wasExecuted=true;
        }

    }
}
