package traffic;

import java.util.Objects;

public class Road {
    private final String name;
    private boolean isFront = false;
    private boolean isRear = false;
    private boolean isOpen = false;

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
        isOpen = isFront;
    }

    public boolean isRear() {
        return isRear;
    }

    public void setRear(boolean rear) {
        isRear = rear;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public void increaseTiming() {
        timing++;
    }

    public void decreaseTiming() {
        timing--;
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
