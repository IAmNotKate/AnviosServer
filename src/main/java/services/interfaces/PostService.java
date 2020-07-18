package services.interfaces;

import models.Post;

import java.util.List;

public interface PostService {
    List<Post> getPostsPage(int page);

    void create(Post post);
}
