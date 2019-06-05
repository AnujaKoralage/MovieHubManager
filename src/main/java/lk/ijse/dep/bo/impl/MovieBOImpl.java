package lk.ijse.dep.bo.impl;

import lk.ijse.dep.bo.MovieBO;
import lk.ijse.dep.dao.MovieDAO;
import lk.ijse.dep.dto.MovieDTO;
import lk.ijse.dep.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieBOImpl implements MovieBO {
    @Autowired
    MovieDAO movie;

    @Override
    public void saveMovie(MovieDTO actorDTO) {

        movie.save(new Movie(actorDTO.getId(),actorDTO.getName()));
    }

    @Override
    public void updateMovie(MovieDTO actorDTO) {

        movie.save(new Movie(actorDTO.getId(),actorDTO.getName()));
    }

    @Override
    public void deleteMovie(MovieDTO actorDTO) {

        movie.delete(new Movie(actorDTO.getId(),actorDTO.getName()));
    }

    @Override
    public List<MovieDTO> getAllMovie() {
        List<Movie> all = movie.findAll();
        List<MovieDTO> movieDTOS = new ArrayList<>();

        for (Movie movie:all) {
            movieDTOS.add(new MovieDTO(movie.getId(),movie.getName()));
        }
        return movieDTOS;
    }

    @Override
    public int getNewId() {
        return movie.getTopByIdOrderByIdDesc()+1;
    }

    @Override
    public String getMoviebyId(int id) {
        return movie.getMovieById(id).getName();
    }
}
