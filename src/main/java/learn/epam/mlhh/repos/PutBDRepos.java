package learn.epam.mlhh.repos;

import learn.epam.mlhh.domain.PutDB;
import org.springframework.data.repository.CrudRepository;

public interface PutBDRepos extends CrudRepository<PutDB, Integer> {
}
