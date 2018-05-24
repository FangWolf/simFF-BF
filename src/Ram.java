public class Ram {
    int id;
    int start;
    int end;
    int size;
    int free;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFree() {
        if (free == 0)
            return "空闲";
        else
            return "使用";
    }

    public void setFree(int free) {
        this.free = free;
    }
}
