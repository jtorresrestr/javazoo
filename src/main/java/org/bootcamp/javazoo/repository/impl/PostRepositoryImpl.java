package org.bootcamp.javazoo.repository.impl;

import org.bootcamp.javazoo.entity.Post;
import org.bootcamp.javazoo.repository.interfaces.IPostRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements IPostRepository {

    private List<Post> postList = new ArrayList<>();

    @Override
    public void addNewPost(Post post) {
        postList.add(post);
    }
}
