package services.implementation;

import converters.post.PostEntityToModelConverter;
import converters.post.PostModelToEntityConverter;
import converters.user.UserEntityToModelConverter;
import database.entities.PostEntity;
import database.repositories.PostRepository;
import database.repositories.UserRepository;
import models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.interfaces.PostService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostModelToEntityConverter postModelToEntityConverter;
    private final PostEntityToModelConverter postEntityToModelConverter;
    private final UserEntityToModelConverter userEntityToModelConverter;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           PostModelToEntityConverter postModelToEntityConverter,
                           PostEntityToModelConverter postEntityToModelConverter,
                           UserEntityToModelConverter userEntityToModelConverter) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postModelToEntityConverter = postModelToEntityConverter;
        this.postEntityToModelConverter = postEntityToModelConverter;
        this.userEntityToModelConverter = userEntityToModelConverter;
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
