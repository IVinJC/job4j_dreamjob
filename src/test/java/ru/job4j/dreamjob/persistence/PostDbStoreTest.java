package ru.job4j.dreamjob.persistence;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDbStoreTest {
    @Test
    public void whenCreatePost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Junior Java Dev.", new City(0, "Moscow"));
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(
                0, "Java Job", "Junior", new City(1, "City"));
        Post expected = new Post(
                post.getId(), "Java Vacancy", "Middle", new City(1, "City"));
        store.add(post);
        store.update(new Post(
                post.getId(), "Java Vacancy", "Middle", new City(1, "City")));
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(expected.getName()));
    }

    @Test
    public void whenFindAllPosts() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Junior", new City(1, "City"));
        Post post2 = new Post(1, "Java Vacancy", "Middle", new City(2, "City2"));
        Post post3 = new Post(2, "Java Vacancy", "Senior", new City(3, "City3"));
        store.add(post);
        store.add(post2);
        store.add(post3);
        Post postInDb = store.findById(post.getId());
        Post postInDb2 = store.findById(post2.getId());
        Post postInDb3 = store.findById(post3.getId());
        assertThat(postInDb.getName(), is(post.getName()));
        assertThat(postInDb2.getName(), is(post2.getName()));
        assertThat(postInDb3.getName(), is(post3.getName()));
    }

    @Test
    public void whenFindPostById() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(
                0, "Java Job", "Junior", new City(1, "City"));
        store.add(post);
        assertThat(store.findById(post.getId()).getName(), is(post.getName()));
        assertThat(store.findById(post.getId()).getId(), is(post.getId()));
    }
}