package learn.epam.mlhh.repository;

import learn.epam.mlhh.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

        List<Users> findAllByName(String name);
}
