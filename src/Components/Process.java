package Components;

import java.util.ArrayList;

public class Process {
    private static int globalID = 0;
    private int pageFaults = 0;
    private ArrayList<Page> logicalMemory = new ArrayList<>();
    private int frames;
    private int framesInUse = 0;


    public void handlePageQuery(Page page){
        if(page.isOnDisk()){
            pageFaults++;
        }
    }

    public void generatePages(int amount){
        for(int i =0; i < amount;i++){
            logicalMemory.add(new Page(globalID++, this));
        }
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public void increasePageFaults(){
        pageFaults++;
    }


    public void setPageFaults(int pageFaults) {
        this.pageFaults = pageFaults;
    }

    public int getFramesInUse() {
        return framesInUse;
    }

    public void setFramesInUse(int framesInUse) {
        this.framesInUse = framesInUse;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public ArrayList<Page> getLogicalMemory() {
        return logicalMemory;
    }



}
