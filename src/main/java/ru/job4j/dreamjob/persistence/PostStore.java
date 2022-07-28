package ru.job4j.dreamjob.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStore {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(4);
    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Работа Junior java developer", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "Работа Middle java developer", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "Работа Senior java developer", LocalDateTime.now()));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public boolean add(Post post) {
        post.setId(id.incrementAndGet());
        return posts.put(post.getId(), post) == null;
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public Post update(Post post) {
       return posts.replace(post.getId(), post);
    }
}