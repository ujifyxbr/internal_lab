package learn.epam.mlhh.repository;
import learn.epam.mlhh.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaces for accessing entities Log in database
 * @author
 * @version 1.1.2
 */

public interface LogRepository extends JpaRepository<Log, Long> {
}
