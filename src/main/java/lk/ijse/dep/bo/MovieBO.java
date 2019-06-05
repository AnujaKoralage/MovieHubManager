package lk.ijse.dep.bo;

import lk.ijse.dep.dto.MovieDTO;

import java.util.List;

public interface MovieBO {

    public void saveMovie(MovieDTO actorDTO) throws Exception;
    public void updateMovie(MovieDTO actorDTO) throws Exception;
    public void deleteMovie(MovieDTO actorDTO) throws Exception;
    public List<MovieDTO> getAllMovie() throws Exception;

    public int getNewId();

    public String getMoviebyId(int id);

}
