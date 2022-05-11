package Components;

public class Frame {
    private final int ID;
    private Page page;

    public Frame(int ID) {
        this.ID = ID;
    }


    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
