package learn.epam.mlhh.repository;

import learn.epam.mlhh.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaces for accessing entities user in database
 * @author
 * @version 1.1.3
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE user_name LIKE :name")
    Users findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.userLock = true WHERE u.userId = :id")
    int userLock(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.userLock = false WHERE u.userId = :id")
    int userUnlock(@Param("id") int id);
}
