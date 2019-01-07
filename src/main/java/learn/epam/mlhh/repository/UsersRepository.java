package learn.epam.mlhh.repository;

import learn.epam.mlhh.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaces for accessing entities user in database
 * @author
 * @version 1.1.3
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE user_name LIKE :name")
    Users findByName(@Param("name") String name);
}
