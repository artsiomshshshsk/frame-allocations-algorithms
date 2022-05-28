package Components;

import java.util.ArrayList;

public class Process {
    private static int globalID = 0;
    private int pageFaults = 0;
    private ArrayList<Page> logicalMemory = new ArrayList<>();
    private int frames;
    private int framesInUse = 0;
    private int timeFramePageFaults = 0;
    private int timeFrame = 10;
    private ArrayList<Page> workSet = new ArrayList<>();


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
        timeFramePageFaults++;
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

    public void setTimeFramePageFaults(int timeFramePageFaults) {
        this.timeFramePageFaults = timeFramePageFaults;
    }

    public int getTimeFramePageFaults() {
        return timeFramePageFaults;
    }

    public void increaseNumberOfFrames(){
        frames++;
    }

    public void reduceNumberOfFrames(){
        if(framesInUse == frames){
            Page toReplace = null;
            int min = Integer.MAX_VALUE;
            for(Page findPage: getLogicalMemory()){
                if(findPage.getLastTimeUsed() < min && findPage.getFrame() != null){
                    min = findPage.getLastTimeUsed();
                    toReplace = findPage;
                }
            }
            toReplace.setFrame(null);
            toReplace.setOnDisk(true);
            framesInUse--;
        }
        frames--;
    }


    public void addToWorkingFrame(Page page){
        if(!workSet.contains(page)){
            workSet.add(page);
        }
    }

    public int workingFrameSize(){
        int size = workSet.size();
        clearWorkingFrame();
        return size;
    }

    public void clearWorkingFrame(){
        workSet = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Process {" +
                " pages=" + logicalMemory.size() +
                ", frames=" + frames +
                ", pageFaults=" + pageFaults +
                '}';
    }
}
