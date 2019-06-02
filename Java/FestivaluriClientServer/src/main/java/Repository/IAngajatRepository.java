package Repository;


import Model.Angajat;

public interface IAngajatRepository extends IRepository<Angajat,Integer> {
    Angajat findByUsername(String username);
}
