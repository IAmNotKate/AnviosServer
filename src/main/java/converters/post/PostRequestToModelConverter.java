package converters.post;

import models.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import restmodels.requests.CreatePostRequest;

import java.util.Calendar;

public class PostRequestToModelConverter implements Converter<CreatePostRequest, Post> {
    @Override
    @NotNull
    public Post convert(@NotNull CreatePostRequest request) {
        Post post = new Post();
        post.setText(request.getText());
        post.setTitle(request.getTitle());
        post.setDateCreated(Calendar.getInstance());
        return post;
    }
}
