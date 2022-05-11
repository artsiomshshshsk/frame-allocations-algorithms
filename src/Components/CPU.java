package Components;

import java.util.ArrayList;

import Allocation.FrameAllocation;
import Components.*;

public class CPU {
    private ArrayList<Process> processes;
    private ArrayList<Page> queries;
    private RAM RAM;
    private int time = 0;
    private FrameAllocation frameAllocation;

    public CPU(ArrayList<Page> queries, int RAMSize, ArrayList<Process> processes, FrameAllocation frameAllocation) {
        this.processes = processes;
        this.queries = queries;
        this.frameAllocation = frameAllocation;
        RAM = new RAM(RAMSize);
    }

    /**
     *
     *
     *  LEAST RECENTLY USE
     *
     */
    public void execute(){
        for(Page page:queries){
            page.setLastTimeUsed(time++);
            allocate(processes,RAM);

            if(page.isOnDisk()){
                page.getProcess().increasePageFaults();
                if(page.getProcess().getFramesInUse() < page.getProcess().getFrames()){
                    RAM.addPage(page);
                }else{
                    replace(page);
                }
            }
        }
        stats();
    }



    public void stats(){
        int totalPageFaults = 0;

        for(Process process: processes){
            totalPageFaults += process.getPageFaults();
        }

        System.out.println("Total page faults:"+totalPageFaults);
        System.out.printf("Average for %d processes:%.2f\n",processes.size(),(double)totalPageFaults/(double)processes.size());
    }

    public void replace(Page page){
        Process process = page.getProcess();
        int min = Integer.MAX_VALUE;
        Page toReplace = null;


        for(Page findPage: process.getLogicalMemory()){
            if(!findPage.equals(page)){
                if(findPage.getLastTimeUsed() < min && findPage.getFrame() != null){
                    min = findPage.getLastTimeUsed();
                    toReplace = findPage;
                    break;
                }
            }
        }
        page.setFrame(toReplace.getFrame());
        toReplace.setOnDisk(true);
        page.setOnDisk(false);
        toReplace.getFrame().setPage(page);
        toReplace.setFrame(null);
    }


    public void setFrameAllocation(FrameAllocation frameAllocation) {
        this.frameAllocation = frameAllocation;
        reset();
    }

    private void reset(){
        for(Process process:processes){
            process.setFramesInUse(0);
            process.setPageFaults(0);
        }

        for(Page page:queries){
            page.setOnDisk(true);
            page.setFrame(null);
        }

        RAM = new RAM(RAM.getFrames().size());
    }

    private void allocate(ArrayList<Process> processes, RAM ram){
        frameAllocation.allocateFrames(processes,ram);
    }

}
