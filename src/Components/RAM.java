package Components;

import java.util.ArrayList;

public class RAM {
    private ArrayList<Frame> frames;

    public RAM(int size) {
        frames = new ArrayList<>(size);
        setFrames(size);
    }

    private void setFrames(int size){
        for(int i = 0; i<size;i++){
            frames.add(new Frame(i));
        }
    }

    public int amountOfFreeFrames(){
        int amount = 0;
        for(Frame frame:frames)
            if(frame.getPage() == null) amount++;
        return amount;
    }

    public void addPage(Page page){
        for(Frame frame:frames){
            if(frame.getPage() == null){
                frame.setPage(page);
                page.setFrame(frame);
                int framesInUse = page.getProcess().getFramesInUse();
                page.getProcess().setFramesInUse(framesInUse + 1);
                page.setOnDisk(false);
                break;
            }
        }
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }
}
