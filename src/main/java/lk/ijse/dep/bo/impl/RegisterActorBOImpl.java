package lk.ijse.dep.bo.impl;

import lk.ijse.dep.bo.ActorRegisterBO;
import lk.ijse.dep.dao.ActorRegisterDAO;
import lk.ijse.dep.dto.ActorRegistrationDTO;
import lk.ijse.dep.entities.Actor;
import lk.ijse.dep.entities.ActorRegistration;
import lk.ijse.dep.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterActorBOImpl implements ActorRegisterBO {
    @Autowired
    ActorRegisterDAO actorDAO;

    @Override
    public void makeRegistration(ActorRegistrationDTO actorRegistrationDTO) {
        actorDAO.save(new ActorRegistration(actorRegistrationDTO.getId(),
                actorRegistrationDTO.getRole(),
                new Actor(actorRegistrationDTO.getActorid(),null,9),
                new Movie(actorRegistrationDTO.getMovieID(),null)));
    }

    @Override
    public int getID() {
        return actorDAO.getTopByIdOrderByIdDesc()+1;
    }

    @Override
    public List<ActorRegistrationDTO> getAllRegs() {
        List<ActorRegistration> all = actorDAO.findAll();
        List<ActorRegistrationDTO> list = new ArrayList<>();

        for (ActorRegistration registration:all) {
            list.add(new ActorRegistrationDTO(registration.getId(),registration.getRole(),registration.getMovie().getId(),registration.getActor().getId()));
        }
        return list;
    }
}
