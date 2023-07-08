package traffic;

import java.util.Objects;

public class Road {
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_RESET = "\u001B[0m";
    private final String name;
    private boolean isFront = false;
    private boolean isRear = false;

    private int timing;

    public Road(String name) {
        this.name = name;
        this.timing = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isFront() {
        return isFront;
    }

    public void setFront(boolean front) {
        isFront = front;
    }

    public boolean isRear() {
        return isRear;
    }

    public void setRear(boolean rear) {
        isRear = rear;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public void increaseTiming(int value) {
        timing += value;
    }

    public void decreaseTiming() {
        timing--;
    }

    public String getRoadStatus() {
        return String.format("%s will be %s for %ds.%s",
                name,
                isFront ? ANSI_GREEN + "open" : ANSI_RED + "closed",
                timing,
                ANSI_RESET);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Objects.equals(name, road.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
