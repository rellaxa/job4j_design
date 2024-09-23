package ru.job4j.gc.leak;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private List<Post> posts = new ArrayList<>();

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public Post add(Post post) {
        int id = atomicInteger.getAndIncrement();
        post.setId(id);
        posts.add(post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public List<Post> getPosts() {
        return posts;
    }
}