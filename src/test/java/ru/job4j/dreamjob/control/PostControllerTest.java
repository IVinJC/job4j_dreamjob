package ru.job4j.dreamjob.control;

import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class PostControllerTest {
    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        HttpSession session = mock(HttpSession.class);
        String page = postController.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        City city = new City(1, "City");
        Post input = new Post(1, "New post", "description", city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.createPost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenformAddPost() {
        Post input = new Post(1, "New post");
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        Model model = mock(Model.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.addPost(model);
        assertThat(page, is("addPost"));
    }

    @Test
    public void whenUpdatePost() {
        City city = new City(1, "City");
        Post post = new Post(1, "New post", "description", city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.updatePost(post);
        verify(postService).update(post);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenformUpdatePost() {
        City city = new City(1, "City");
        Post post = new Post(1, "New post", "description", city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        Model model = mock(Model.class);
        when(postService.findById(0)).thenReturn(post);
        when(cityService.getAllCities()).thenReturn(List.of(city));
        String page = postController.formUpdatePost(model, 0);
        assertThat(page, is("updatePost"));
    }
}