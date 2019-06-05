import lk.ijse.dep.AppConfig;
import lk.ijse.dep.dao.ActorDAO;
import lk.ijse.dep.dao.MovieDAO;
import lk.ijse.dep.entities.Actor;
import lk.ijse.dep.entities.Movie;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tast {

    public static AnnotationConfigApplicationContext ctx;

    public static void main(String[] args) {

        ctx= new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();

        MovieDAO movieDAO = ctx.getBean(MovieDAO.class);
        int id =1;
        Movie movieById = movieDAO.getMovieById(id);
        System.out.println(movieById);

        ActorDAO bean = ctx.getBean(ActorDAO.class);
        Actor actorById = bean.getActorById(id);
        System.out.println(actorById);
    }

}
