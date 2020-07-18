package controllers;

import converters.post.PostModelToResponseConverter;
import converters.post.PostRequestToModelConverter;
import models.DeviceBound;
import models.Post;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restmodels.requests.CreatePostRequest;
import restmodels.responses.PostResponse;
import services.implementation.DeviceBoundServiceImpl;
import services.implementation.PostServiceImpl;
import services.implementation.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/content/post")
public class PostsController {
    private final DeviceBoundServiceImpl deviceBoundService;
    private final PostServiceImpl postService;
    private final UserServiceImpl userService;
    private final PostRequestToModelConverter postRequestToModelConverter;
    private final PostModelToResponseConverter postModelToResponseConverter;

    @Autowired
    public PostsController(PostServiceImpl postService,
                           DeviceBoundServiceImpl deviceBoundService,
                           UserServiceImpl userService,
                           PostRequestToModelConverter postRequestToModelConverter,
                           PostModelToResponseConverter postModelToResponseConverter) {
        this.postService = postService;
        this.deviceBoundService = deviceBoundService;
        this.userService = userService;
        this.postRequestToModelConverter = postRequestToModelConverter;
        this.postModelToResponseConverter = postModelToResponseConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestHeader(name = "device_token") String token,
                                        @RequestBody CreatePostRequest request) {
        DeviceBound deviceBound = deviceBoundService.validateToken(token);
        if (deviceBound == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Post post = postRequestToModelConverter.convert(request);
        User user = userService.findUserById(deviceBound.getUserId());
        post.setUser(user);
        postService.create(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> getPostsPage(@RequestParam int page) {
        List<Post> posts = postService.getPostsPage(page);
        List<PostResponse> responses = new ArrayList<>();
        for (Post post : posts) {
            responses.add(postModelToResponseConverter.convert(post));
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
