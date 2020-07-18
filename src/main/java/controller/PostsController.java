package controller;

import converter.post.PostModelToResponseConverter;
import converter.post.PostRequestToModelConverter;
import model.DeviceBound;
import model.Post;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restmodel.request.CreatePostRequest;
import restmodel.response.PostResponse;
import service.implementation.DeviceBoundServiceImpl;
import service.implementation.PostServiceImpl;
import service.implementation.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content/post")
public class PostsController {
    private final DeviceBoundServiceImpl deviceBoundService;
    private final PostServiceImpl postService;
    private final UserServiceImpl userService;
    private final PostRequestToModelConverter postRequestToModelConverter = new PostRequestToModelConverter();
    private final PostModelToResponseConverter postModelToResponseConverter = new PostModelToResponseConverter();

    @Autowired
    public PostsController(PostServiceImpl postService,
                           DeviceBoundServiceImpl deviceBoundService,
                           UserServiceImpl userService) {
        this.postService = postService;
        this.deviceBoundService = deviceBoundService;
        this.userService = userService;
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
        List<PostResponse> responses = postService.getPostsPage(page).stream()
                .map(postModelToResponseConverter::convert).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
