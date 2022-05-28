package Components;

public class Page {
    private int lastTimeUsed;
    private Process process;
    private final int ID;
    private boolean isOnDisk = true;
    private Frame frame;

    public Page(int id, Process process) {
        ID = id;
        this.process = process;
    }

    public boolean isOnDisk() {
        return isOnDisk;
    }

    public Process getProcess() {
        return process;
    }

    public int getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setOnDisk(boolean onDisk) {
        isOnDisk = onDisk;
    }

    public void setLastTimeUsed(int lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        if (lastTimeUsed != page.lastTimeUsed) return false;
        if (ID != page.ID) return false;
        if (isOnDisk != page.isOnDisk) return false;
        if (process != null ? !process.equals(page.process) : page.process != null) return false;
        return frame != null ? frame.equals(page.frame) : page.frame == null;
    }

    @Override
    public int hashCode() {
        int result = lastTimeUsed;
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + ID;
        result = 31 * result + (isOnDisk ? 1 : 0);
        result = 31 * result + (frame != null ? frame.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "lastTimeUsed=" + lastTimeUsed +
                ", ID=" + ID +
                ", isOnDisk=" + isOnDisk +
                ", frame=" + frame +
                '}';
    }
}
