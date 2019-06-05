package lk.ijse.dep.dao;

import lk.ijse.dep.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieDAO extends JpaRepository<Movie,Integer> {
    @Query("select m from Movie m where m.id=?1")
    public Movie getMovieById(int id);

    @Query(value = "select id from movie order by id desc limit 1",nativeQuery = true)
    public int getTopByIdOrderByIdDesc();
}
