package lk.ijse.dep.dto;

public class ActorRegistrationDTO {

    private int id;
    private String role;
    private int movieID;
    private int actorid;

    @Override
    public String toString() {
        return "ActorRegistrationDTO{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", movieID=" + movieID +
                ", actorid=" + actorid +
                '}';
    }

    public ActorRegistrationDTO() {
    }

    public ActorRegistrationDTO(int id, String role, int movieID, int actorid) {
        this.id = id;
        this.role = role;
        this.movieID = movieID;
        this.actorid = actorid;
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

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getActorid() {
        return actorid;
    }

    public void setActorid(int actorid) {
        this.actorid = actorid;
    }
}
