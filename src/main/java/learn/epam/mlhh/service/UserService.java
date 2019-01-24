package learn.epam.mlhh.service;

import learn.epam.mlhh.entity.Users;
import learn.epam.mlhh.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class that implements methods for working with an entity
 * @author
 * @version 1.1.3
 */
@Service
public class UserService {

    @Autowired
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public void createUser(Users users){
        usersRepository.save(users);
    }

    public List<Users> findAll(){
        return usersRepository.findAll();
    }

    public Users findByName(String name) {
        return usersRepository.findByName(name);
    }

    public Integer userLock(int id) {
        return usersRepository.userLock(id);
    }

    public Integer userUnlock(int id) {
        return usersRepository.userUnlock(id);
    }
}
