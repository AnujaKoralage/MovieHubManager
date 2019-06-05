package lk.ijse.dep.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ActorRegistration {

    @Id
    private int id;
    private String role;

    @ManyToOne
    @JoinColumn(name = "actor_id",referencedColumnName = "id")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    private Movie movie;

    public ActorRegistration() {
    }

    public ActorRegistration(int id, String role, Actor actor, Movie movie) {
        this.id = id;
        this.role = role;
        this.actor = actor;
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ActorRegistration{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
