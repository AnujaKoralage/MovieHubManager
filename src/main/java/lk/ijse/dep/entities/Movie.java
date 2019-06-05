package lk.ijse.dep.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Movie {

    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "movie")
    private List<ActorRegistration> actorRegistrationList;

    public Movie() {
    }

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<ActorRegistration> getActorRegistrationList() {
        return actorRegistrationList;
    }

    public void setActorRegistrationList(List<ActorRegistration> actorRegistrationList) {
        this.actorRegistrationList = actorRegistrationList;
    }
}
