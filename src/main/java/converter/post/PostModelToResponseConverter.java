package converter.post;

import model.Post;
import model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import restmodel.response.PostResponse;

public class PostModelToResponseConverter implements Converter<Post, PostResponse> {
    private final UserToPostCreatorResponseConverter userConverter = new UserToPostCreatorResponseConverter();

    @Override
    @NotNull
    public PostResponse convert(@NotNull Post post) {
        PostResponse response = new PostResponse();
        response.setDateCreated(post.getDateCreated());
        response.setPostId(post.getPostId());
        response.setTitle(post.getTitle());
        response.setText(post.getText());
        response.setCreator(userConverter.convert(post.getUser()));
        return response;
    }

    private static class UserToPostCreatorResponseConverter implements Converter<User, PostResponse.PostCreatorResponse> {
        @Override
        public PostResponse.PostCreatorResponse convert(User user) {
            PostResponse.PostCreatorResponse response = new PostResponse.PostCreatorResponse();
            response.setUserId(user.getUserId());
            response.setUsername(user.getUsername());
            return response;
        }
    }
}
