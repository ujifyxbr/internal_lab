package learn.epam.mlhh.service;

import learn.epam.mlhh.entity.Log;
import learn.epam.mlhh.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class that implements methods for working with an entity
 * @author
 * @version 1.1.2
 */
@Service
public class LogService {

    @Autowired
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public void createLog(Log log){
        logRepository.save(log);
    }
}
