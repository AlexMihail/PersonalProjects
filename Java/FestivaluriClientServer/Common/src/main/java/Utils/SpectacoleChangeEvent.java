package Utils;

import Model.Spectacol;


import java.io.Serializable;

public abstract class SpectacoleChangeEvent implements Event, Serializable {

    private ChangeEventType type;
    private Spectacol data,oldData;

    public SpectacoleChangeEvent(ChangeEventType type, Spectacol data,Spectacol oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }


    public SpectacoleChangeEvent(ChangeEventType type, Spectacol data) {
        this.type = type;
        this.data = data;
    }


    public ChangeEventType getType() {
        return type;
    }

    public Spectacol getData() {
        return data;
    }

    public Spectacol getOldData() {
        return oldData;
    }
}