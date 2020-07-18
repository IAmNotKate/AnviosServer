package service.implementation;

import converter.post.PostEntityToModelConverter;
import converter.post.PostModelToEntityConverter;
import converter.user.UserEntityToModelConverter;
import database.entity.PostEntity;
import database.repository.PostRepository;
import database.repository.UserRepository;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.PostService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostModelToEntityConverter postModelToEntityConverter = new PostModelToEntityConverter();
    private final PostEntityToModelConverter postEntityToModelConverter = new PostEntityToModelConverter();
    private final UserEntityToModelConverter userEntityToModelConverter = new UserEntityToModelConverter();

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getPostsPage(int page) {
        List<PostEntity> entities = postRepository.findPostEntitiesByIdBetweenOrderByDateCreatedDesc(1 + 30 * (page - 1), 1 + 30 * page);
        List<Post> list = new ArrayList<>();
        for (PostEntity entity : entities) {
            Post post = postEntityToModelConverter.convert(entity);
            post.setUser(userEntityToModelConverter.convert(
                    userRepository.findUserEntityById(entity.getCreatorId())));
            list.add(post);
        }
        return list;
    }

    @Override
    public void create(Post post) {
        postRepository.save(postModelToEntityConverter.convert(post));
    }
}
