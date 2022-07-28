package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateStore;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;

@ThreadSafe
@Service
public class CandidateService {
    private final CandidateStore store;

    public CandidateService(CandidateStore store) {
        this.store = store;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public boolean add(Candidate candidate) {
        return store.add(candidate);
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public Candidate update(Candidate candidate) {
        return store.update(candidate);
    }
}
