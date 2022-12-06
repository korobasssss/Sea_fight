package cs.vsu.ru.Korobeynikova_A_V.Figure;

import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public interface Figure {

    enum Status {
        ALIVE,
        KILLED
    }

    Coordinate getPosition();

    void setPosition(Coordinate position);

    Ship.Type getShipType();

    void setShipType(Ship.Type shipType);

    Ship.Orientation getOrientation();

    void setOrientation(Ship.Orientation orientation);

    Status getStatus();

    void setStatus(Ship.Status status);

}
