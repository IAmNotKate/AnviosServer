package converters.post;

import database.entities.PostEntity;
import models.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.util.Calendar;

public class PostEntityToModelConverter implements Converter<PostEntity, Post> {
    @Override
    @NotNull
    public Post convert(@NotNull PostEntity postEntity) {
        Post post = new Post();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(postEntity.getDateCreated().getTime());
        post.setPostId(postEntity.getId());
        post.setTitle(postEntity.getTitle());
        post.setText(postEntity.getText());
        post.setDateCreated(calendar);
        return post;
    }
}
