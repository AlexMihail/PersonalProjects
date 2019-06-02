package Repository;

import java.util.List;

public interface IRepository<E,ID> {
    E findOne(ID id);
    List<E> findAll();
    void save(E elem);
    void delete(ID id);
    void update(ID id, E entity);
}
