package Repository;

import Model.Bilet;

public interface IBiletRepository extends IRepository<Bilet,Integer> {
    Bilet findByClient(String username);
}
