package ru.job4j.dreamjob.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateStore;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class CandidateController {
    private final CandidateStore store = CandidateStore.instOf();
    private final AtomicInteger id = new AtomicInteger(4);

    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", store.findAll());
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addCandidate(Model model) {
        model.addAttribute("addCandidate", new Candidate(0, "Добавьте кандидата"));
        return "addCandidate";
    }

    @PostMapping("/createCandidate")
    public String createCandidate(@ModelAttribute Candidate candidate) {
        candidate.setId(id.getAndIncrement());
        candidate.setCreated(LocalDateTime.now());
        store.add(candidate);
        return "redirect:/candidates";
    }

    @PostMapping("/updateCandidate")
    public String updateCandidate(@ModelAttribute Candidate candidate) {
        candidate.setCreated(LocalDateTime.now());
        store.update(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/formUpdateCandidate/{candidateId}")
    public String formUpdateCandidate(Model model, @PathVariable("candidateId") int id) {
        model.addAttribute("candidate", store.findById(id));
        return "updateCandidate";
    }
}