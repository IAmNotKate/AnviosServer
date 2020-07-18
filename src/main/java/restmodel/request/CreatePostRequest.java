package restmodel.request;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CreatePostRequest {
    private String title;
    private String text;

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
}
