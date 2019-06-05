package lk.ijse.dep.bo.impl;

import lk.ijse.dep.bo.ActorBO;
import lk.ijse.dep.dao.ActorDAO;
import lk.ijse.dep.dto.ActorDTO;
import lk.ijse.dep.entities.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActorBOImpl implements ActorBO {

    @Autowired
    ActorDAO actorDAO;

    @Override
    public void saveActor(ActorDTO actorDTO) {
        Actor actor = new Actor(actorDTO.getId(),actorDTO.getName(),actorDTO.getAge());

        actorDAO.save(actor);
    }

    @Override
    public void updateActor(ActorDTO actorDTO) {

        actorDAO.save(new Actor(actorDTO.getId(),actorDTO.getName(),actorDTO.getAge()));

    }

    @Override
    public void deleteActor(ActorDTO actorDTO) {

        actorDAO.delete(new Actor(actorDTO.getId(),actorDTO.getName(),actorDTO.getAge()));

    }

    @Override
    public List<ActorDTO> getAllActors() {
        List<Actor> all = actorDAO.findAll();
        List<ActorDTO> actorDTOS = new ArrayList<>();

        for (Actor actor:all) {
            actorDTOS.add(new ActorDTO(actor.getId(),actor.getName(),actor.getAge()));
        }
        return actorDTOS;
    }

    @Override
    public int newId() {
        return actorDAO.getTopByIdOrderByIdDesc()+1;
    }

    @Override
    public String getActorName(int id) {
        return actorDAO.getActorById(id).getName();
    }
}
