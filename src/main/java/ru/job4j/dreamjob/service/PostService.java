package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.persistence.PostDbStore;

import javax.annotation.concurrent.ThreadSafe;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
public class PostService {
    private final PostDbStore store;
    private final CityService cityService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public PostService(PostDbStore store, CityService cityService) {
        this.store = store;
        this.cityService = cityService;
    }

    public Collection<Post> findAll() {
        List<Post> posts = store.findAll();
        posts.forEach(
                post -> {
                    post.setCity(
                            cityService.findById(post.getCity().getId()));
                    String createdString = post.getCreated().format(formatter);
                    LocalDateTime created = LocalDateTime.parse(createdString, formatter);
                    post.setCreated(created);
                }
        );
        return posts;
    }

    public Post add(Post post) {
        return store.add(post);
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public void update(Post post) {
        store.update(post);
    }
}
