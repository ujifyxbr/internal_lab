package learn.epam.mlhh.service;

import learn.epam.mlhh.entity.Candidate;
import learn.epam.mlhh.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public void createCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public List<Candidate> findAll(){
        return candidateRepository.findAll();
    }

    public Candidate findById(Long candidateId){
        return candidateRepository.findById(candidateId).orElse(null);
    }

    public List<Candidate> findAllByName(String name){
        return candidateRepository.findAllByName(name);
    }
}
