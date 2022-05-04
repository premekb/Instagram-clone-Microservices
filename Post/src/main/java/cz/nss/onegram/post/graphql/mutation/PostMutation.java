package cz.nss.onegram.post.graphql.mutation;

import cz.nss.onegram.post.graphql.input.post.CreatePostInput;
import cz.nss.onegram.post.graphql.input.post.DeletePostInput;
import cz.nss.onegram.post.model.Post;
import cz.nss.onegram.post.security.model.UserDetailsImpl;
import cz.nss.onegram.post.service.interfaces.PostService;
import cz.nss.onegram.post.service.interfaces.UserService;
import cz.nss.onegram.post.util.InputMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final InputMapper mapper;

    private final PostService postService;

    private final UserService userService;

    public Post createPost(CreatePostInput input){
        Post post = mapper.convertToEntity(input);
        UserDetailsImpl user = userService.getCurrentUser();
        post.setAuthorId(user.getId());
        postService.persist(post);
        log.info("Post created: {} By user: {}", post, user);
        return post;
    }

    @PreAuthorize("@userServiceImpl.userCreatedPost(#input.id, @userServiceImpl.getCurrentUser())")
    public Integer deletePost(DeletePostInput input){
        Post post = postService.findById(input.getId());
        if (post != null){
            log.info("Deleting post: {}", post);
            postService.delete(post);
            log.info("Post id {} deleted", input.getId());
            return 1;
        }

        else {
            return 0;
        }
    }
}
