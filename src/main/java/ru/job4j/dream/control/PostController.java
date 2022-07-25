package ru.job4j.dream.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {

    private final PostStore store = PostStore.instOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", store.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post(0, "Заполните поле"));
        return "addPost";
    }

/**Cоздание метода через HttpServletRequest*/
    /**
     * @PostMapping("/createPost") public String createPost(HttpServletRequest req) {
     * String name = req.getParameter("name");
     * System.out.println(name);
     * store.add(new Post(1, name));
     * return "redirect:/posts";
     * }
     */

/**    Теперь доработаем контроллер. Вместо HttpServletRequest напишем Post и добавим аннотацию @ModelAttribute.
    @ModelAttribute сообщаем Spring, чтобы тот собрал объект Post из параметров запроса.*/
    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        store.add(post);
        return "redirect:/posts";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        store.update(post);
        return "redirect:/posts";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("post", store.findById(id));
        return "updatePost";
    }
}