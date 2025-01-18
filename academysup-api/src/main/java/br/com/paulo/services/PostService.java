package br.com.paulo.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.paulo.dto.PostDTO;
import br.com.paulo.entities.Post;
import br.com.paulo.entities.User;
import br.com.paulo.exceptions.NotFoundEntity;
import br.com.paulo.mappers.PostMapper;
import br.com.paulo.repositories.PostRepository;
import br.com.paulo.repositories.UserRepository;
import br.com.paulo.resources.PostResource;
import br.com.paulo.versions.VOsv1.PostVO;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostMapper mapper;

    public Page<PostVO> findAll(Pageable pageable) {
		
		Page<Post> posts = repository.findAll(pageable);

		var postsVo = posts.map(p -> mapper.convertePostForPostVO(p));
		postsVo.map(p -> p.add(linkTo(methodOn(PostResource.class).findById(p.getKey())).withSelfRel()));

		return postsVo;
	}

    public PostVO findById(Long id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new NotFoundEntity("ID: " + id + " not found. Please enter a valid value!"));
        
        PostVO postVo = mapper.convertePostForPostVO(post);
        postVo.add(linkTo(methodOn(PostResource.class).findById(postVo.getKey())).withSelfRel());

        return postVo;
    }

    public PostVO insert(PostDTO postDto) {
        User user = userRepository.findById(postDto.userId())
                .orElseThrow(() -> new NotFoundEntity("User ID: " + postDto.userId() + " not found. Please enter a valid value!"));

        Post post = new Post();
        post.setCreator(user);
        post.setTitle(postDto.title());
        post.setDescription(postDto.description());
        post.setMoment(Instant.now());

        repository.save(post);

        PostVO postVo = mapper.convertePostForPostVO(post);
        postVo.add(linkTo(methodOn(PostResource.class).findById(postVo.getKey())).withSelfRel());

        return postVo;
    }

    public PostVO update(PostDTO postDto) {
        if (!repository.existsById(postDto.id())) {
            throw new NotFoundEntity("Unable to update. ID: " + postDto.id() + " not found. Please enter a valid value!");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new AccessDeniedException("You do not have permission to edit this post");
        }

        User user = (User) authentication.getPrincipal();
        Post post = repository.findById(postDto.id()).orElseThrow(() -> 
            new NotFoundEntity("Post ID: " + postDto.id() + " not found. Please enter a valid value!")
        );

        if (!post.getCreator().getId().equals(user.getId())) {
            throw new AccessDeniedException("You do not have permission to edit this post");
        }

        post.setTitle(postDto.title());
        post.setDescription(postDto.description());
        post.setMoment(Instant.now());

        repository.save(post);

        PostVO postVo = mapper.convertePostForPostVO(post);
        postVo.add(linkTo(methodOn(PostResource.class).findById(postVo.getKey())).withSelfRel());

        return postVo;
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundEntity("Unable to delete! ID: " + id + " not found. Please enter a valid value!");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new AccessDeniedException("You do not have permission to delete this post");
        }

        User user = (User) authentication.getPrincipal();
        Post post = repository.findById(id).orElseThrow(() -> 
            new NotFoundEntity("Post ID: " + id + " not found. Please enter a valid value!")
        );

        if (!post.getCreator().getId().equals(user.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this post");
        }

        repository.deleteById(id);
    }
}
