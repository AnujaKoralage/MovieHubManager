package lk.ijse.dep.dao;

import lk.ijse.dep.entities.ActorRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActorRegisterDAO extends JpaRepository<ActorRegistration,Integer> {

    @Query(value = "select id from actorregistration order by id desc limit 1",nativeQuery = true)
    public int getTopByIdOrderByIdDesc();

}
