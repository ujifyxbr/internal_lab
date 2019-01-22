package learn.epam.mlhh.repository;

import learn.epam.mlhh.entity.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateAddRepo extends CrudRepository<Candidate, Long> {
}
