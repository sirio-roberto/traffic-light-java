package traffic;

import java.util.Objects;

public class Road {
    private final String name;
    private boolean isFront = false;
    private boolean isRear = false;

    public Road(String name) {
        this.name = name;
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
