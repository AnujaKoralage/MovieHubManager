package lk.ijse.dep.bo;

import lk.ijse.dep.dao.ActorDAO;
import lk.ijse.dep.dto.ActorRegistrationDTO;

import java.util.List;

public interface ActorRegisterBO {

    public void makeRegistration(ActorRegistrationDTO actorRegistrationDTO) throws Exception;
    public int getID();
    public List<ActorRegistrationDTO> getAllRegs();

}
