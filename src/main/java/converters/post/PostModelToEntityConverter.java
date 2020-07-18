package converters.post;

import database.entities.PostEntity;
import models.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public class PostModelToEntityConverter implements Converter<Post, PostEntity> {
    @Override
    @NotNull
    public PostEntity convert(@NotNull Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setCreatorId(post.getUser().getUserId());
        postEntity.setTitle(post.getTitle());
        postEntity.setText(post.getText());
        postEntity.setDateCreated(new Timestamp(post.getDateCreated().getTimeInMillis()));
        return postEntity;
    }
}
