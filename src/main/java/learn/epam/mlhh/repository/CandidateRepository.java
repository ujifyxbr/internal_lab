package learn.epam.mlhh.repository;

import learn.epam.mlhh.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByName(String name);

    /*@Query("SELECT u FROM Candidate u WHERE developer like 'Java'")     //собственный запрос
    List<Candidate> findWhereDevelop();*/
}
