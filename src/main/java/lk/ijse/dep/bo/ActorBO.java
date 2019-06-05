package lk.ijse.dep.bo;

import lk.ijse.dep.dto.ActorDTO;

import java.util.List;

public interface ActorBO {

    public void saveActor(ActorDTO actorDTO) throws Exception;
    public void updateActor(ActorDTO actorDTO) throws Exception;
    public void deleteActor(ActorDTO actorDTO) throws Exception;
    public List<ActorDTO> getAllActors() throws Exception;

    public int newId();

    public String getActorName(int id);

}
