package ru.job4j.dreamjob.persistence;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CandidateDbStoreTest {
    Candidate candidate = new Candidate(
            0, "Java Job", "Junior", new byte[]{23, 65, 66}, true);

    @Test
    public void whenAddCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate expected = new Candidate(
                candidate.getId(), "Java Vacancy", "Middle", new byte[]{23, 65, 66}, true);
        store.add(candidate);
        store.update(new Candidate(
                candidate.getId(), "Java Vacancy", "Middle", new byte[]{23, 65, 66}, true));
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(expected.getName()));
    }

    @Test
    public void whenFindAllCandidates() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate1 = new Candidate(
                1, "Java Vacancy", "Middle", new byte[]{23, 65, 66}, true);
        Candidate candidate2 = new Candidate(
                2, "Java Vacancy", "Senior", new byte[]{23, 65, 66}, true);
        store.add(candidate);
        store.add(candidate1);
        store.add(candidate2);
        Candidate candidateInDb = store.findById(candidate.getId());
        Candidate candidateInDb2 = store.findById(candidate1.getId());
        Candidate candidateInDb3 = store.findById(candidate2.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
        assertThat(candidateInDb2.getName(), is(candidate1.getName()));
        assertThat(candidateInDb3.getName(), is(candidate2.getName()));
    }

    @Test
    public void whenCandidateFindById() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate(
                0, "Java Job", "Junior", new byte[]{23, 65, 66}, true);
        store.add(candidate);
        assertThat(store.findById(candidate.getId()).getName(), is(candidate.getName()));
        assertThat(store.findById(candidate.getId()).getId(), is(candidate.getId()));
    }
}