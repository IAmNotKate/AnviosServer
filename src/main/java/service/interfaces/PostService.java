package service.interfaces;

import model.Post;

import java.util.List;

public interface PostService {
    List<Post> getPostsPage(int page);

    void create(Post post);
}
