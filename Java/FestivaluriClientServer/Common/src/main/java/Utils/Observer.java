package Utils;

import Model.Spectacol;

import java.util.List;

public interface Observer {
    void update(List<Spectacol> spect);
}