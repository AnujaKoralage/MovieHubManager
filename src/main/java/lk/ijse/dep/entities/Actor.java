package lk.ijse.dep.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Actor {

    @Id
    private int id;
    private String name;
    private int age;

    @OneToMany(mappedBy = "actor")
    private List<ActorRegistration> actorRegistrations;

    public Actor() {
    }

    public Actor(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public List<ActorRegistration> getActorRegistrations() {
        return actorRegistrations;
    }

    public void setActorRegistrations(List<ActorRegistration> actorRegistrations) {
        this.actorRegistrations = actorRegistrations;
    }
}
