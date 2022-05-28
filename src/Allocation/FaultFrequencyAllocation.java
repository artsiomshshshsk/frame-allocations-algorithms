package Allocation;

import Components.Process;
import Components.RAM;

import java.util.ArrayList;

public class FaultFrequencyAllocation implements FrameAllocation{
    private FrameAllocation proportional = new ProportionalAllocation();
    private boolean wasExecuted = false;


    @Override
    public void allocateFrames(ArrayList<Process> processes, RAM ram) {
        if(!wasExecuted){
            proportional.allocateFrames(processes,ram);
            wasExecuted = true;
        }else{
            if(ram.getTimeFrameCount()+1 == ram.getTimeFrame()){
                double max = Double.MIN_VALUE;
                double min = Double.MAX_VALUE;

                Process maxP = null;
                Process minP = null;

                for(Process process: processes){
//                    if(process.getFrames() > 1){
//                        process.reduceNumberOfFrames();
//                    }
                    double faultFrequency = (double) process.getTimeFramePageFaults() / (double) ram.getTimeFrame();
//                    System.out.println(faultFrequency);

                    if(faultFrequency > 0.02){
                        if(ram.amountOfFreeFrames() > 0)
                            process.increaseNumberOfFrames();
                    }
                    if(faultFrequency < 0.002){
                        if(process.getFrames() > 1)
                            process.reduceNumberOfFrames();
                    }

                    if(faultFrequency < min){
                        min = faultFrequency;
                        minP = process;
                    }
                    if(faultFrequency > max){
                        max = faultFrequency;
                        maxP = process;
                    }

                    process.setTimeFramePageFaults(0);
                }
//                System.out.println();
//                System.out.printf(minP + "min fault frequency:%.10f\n", min);
//                System.out.printf(maxP + "max fault frequency:%.10f\n", max);
//                System.out.println();
                ram.setTimeFrameCount(0);
            }else{
                ram.setTimeFrameCount(ram.getTimeFrameCount() + 1);
            }

        }
    }
}
