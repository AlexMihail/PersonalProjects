package Repository;

import Model.Spectacol;

import java.util.List;

public interface ISpectacolRepository extends IRepository<Spectacol,Integer> {
    List<Spectacol> findByDate(String date);
    Spectacol findByName(String name);
}
