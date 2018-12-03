package learn.epam.mlhh.repository;

import learn.epam.mlhh.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaces for accessing entities user in database
 * @author
 * @version 1.1.2
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
}
