package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Александр", "Кандидат Junior java developer", LocalDate.now()));
        candidates.put(2, new Candidate(2, "Виталий", "Кандидат Middle java developer", LocalDate.now()));
        candidates.put(3, new Candidate(3, "Екатерина", "Кандидат Senior java developer", LocalDate.now()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
