package restmodels.responses;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Calendar;

public class PostResponse {
    private Integer postId;
    private String title;
    private String text;
    private Calendar dateCreated;
    private PostCreatorResponse creator;

    @JsonGetter("post_id")
    public Integer getPostId() {
        return postId;
    }

    @JsonSetter("post_id")
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @JsonGetter("title")
    public String getTitle() {
        return title;
    }

    @JsonSetter("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("text")
    public String getText() {
        return text;
    }

    @JsonSetter("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonGetter("date_created")
    public Calendar getDateCreated() {
        return dateCreated;
    }

    @JsonSetter("date_created")
    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonGetter("creator")
    public PostCreatorResponse getCreator() {
        return creator;
    }

    @JsonSetter("creator")
    public void setCreator(PostCreatorResponse creator) {
        this.creator = creator;
    }

    static public class PostCreatorResponse {
        private Integer userId;
        private String username;

        @JsonGetter("user_id")
        public Integer getUserId() {
            return userId;
        }

        @JsonSetter("user_id")
        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        @JsonGetter("username")
        public String getUsername() {
            return username;
        }

        @JsonSetter("username")
        public void setUsername(String username) {
            this.username = username;
        }
    }
}
