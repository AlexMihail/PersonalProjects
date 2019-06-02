package Repository;

import Model.Artist;

public interface IArtistRepository extends IRepository<Artist,Integer> {
    Artist findByName(String username);
}
