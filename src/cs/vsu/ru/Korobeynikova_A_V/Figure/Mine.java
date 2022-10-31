package cs.vsu.ru.Korobeynikova_A_V.Figure;


public class Mine {
    int[] position;
    Status status;

    public enum Status {
        ACTIVATED,
        NOT_ACTIVATED
    }

    public Mine(int[] position, Status status) {
        super();
        this.position = position;
        this.status = status;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
