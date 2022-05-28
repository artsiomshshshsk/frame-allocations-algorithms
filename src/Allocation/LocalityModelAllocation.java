package Allocation;

import Components.Process;
import Components.RAM;

import java.util.ArrayList;

public class LocalityModelAllocation implements FrameAllocation{ // zasada symetrii odwołań
    private boolean wasExecuted = false;
    private FrameAllocation proportional = new ProportionalAllocation();

    @Override

    public void allocateFrames(ArrayList<Process> processes, RAM ram) {
        if(!wasExecuted){
            proportional.allocateFrames(processes,ram);
            wasExecuted = true;
        }else{
            if(ram.getTimeFrameCount()+1 == ram.getLocalityModelTimeFrame()){
                for (Process process: processes){
                    int workingFrameSize = process.workingFrameSize();

                    if(workingFrameSize < process.getFrames()){
                        while(workingFrameSize < process.getFrames()){
                            process.reduceNumberOfFrames();
                        }
                    }
                    if(workingFrameSize > process.getFrames()){
                        while (workingFrameSize > process.getFrames()){
                            process.increaseNumberOfFrames();
                        }
                    }
                }
                ram.setTimeFrameCount(0);
            }else{
                ram.setTimeFrameCount(ram.getTimeFrameCount() + 1);
            }
        }
    }
}
