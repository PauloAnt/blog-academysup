package br.com.paulo.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.paulo.dto.CommentDTO;
import br.com.paulo.entities.Comment;
import br.com.paulo.entities.Post;
import br.com.paulo.entities.User;
import br.com.paulo.exceptions.NotFoundEntity;
import br.com.paulo.mappers.CommentMapper;
import br.com.paulo.repositories.CommentRepository;
import br.com.paulo.repositories.PostRepository;
import br.com.paulo.repositories.UserRepository;
import br.com.paulo.resources.CommentResource;
import br.com.paulo.versions.VOsv1.CommentVO;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentMapper mapper;

    public List<CommentVO> findAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentVO> commentsVo = new ArrayList<>();

        for (Comment comment : comments) {
            CommentVO commentVo = mapper.converteCommentForCommentVO(comment);
            commentVo.add(linkTo(methodOn(CommentResource.class).findById(commentVo.getKey())).withSelfRel());
            commentsVo.add(commentVo);
        }

        return commentsVo;
    }

    public CommentVO findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntity("ID: " + id + " not found. Please enter a valid value!"));
        CommentVO commentVo = mapper.converteCommentForCommentVO(comment);
        commentVo.add(linkTo(methodOn(CommentResource.class).findById(commentVo.getKey())).withSelfRel());

        return commentVo;
    }

    public CommentVO insert(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.userId())
                .orElseThrow(() -> new NotFoundEntity("User ID: " + commentDTO.userId() + " not found. Please enter a valid value!"));

        Post post = postRepository.findById(commentDTO.postId())
                .orElseThrow(() -> new NotFoundEntity("Post ID: " + commentDTO.postId() + " not found. Please enter a valid value!"));

        Comment comment = new Comment();
        comment.setCreator(user);
        comment.setPost(post);
        comment.setDescription(commentDTO.description());
        comment.setMoment(commentDTO.moment());

        commentRepository.save(comment);

        CommentVO resultVo = mapper.converteCommentForCommentVO(comment);
        resultVo.add(linkTo(methodOn(CommentResource.class).findById(resultVo.getKey())).withSelfRel());

        return resultVo;
    }

    public CommentVO update(CommentDTO commentDTO) {
        if (!commentRepository.existsById(commentDTO.id())) {
            throw new NotFoundEntity("Unable to update. ID: " + commentDTO.id() + " not found. Please enter a valid value!");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new AccessDeniedException("You do not have permission to edit this comment");
        }
        
        User user = (User) authentication.getPrincipal();
        Comment comment = commentRepository.findById(commentDTO.id()).orElseThrow(() -> 
            new NotFoundEntity("Comment ID: " + commentDTO.id() + " not found. Please enter a valid value!")
        );

        if (!comment.getCreator().getId().equals(user.getId())) {
            throw new AccessDeniedException("You do not have permission to edit this comment");
        }

        Post post = postRepository.findById(commentDTO.postId())
                .orElseThrow(() -> new NotFoundEntity("Post ID: " + commentDTO.postId() + " not found. Please enter a valid value!"));

        comment.setPost(post);
        comment.setDescription(commentDTO.description());
        comment.setMoment(commentDTO.moment());

        commentRepository.save(comment);

        CommentVO resultVo = mapper.converteCommentForCommentVO(comment);
        resultVo.add(linkTo(methodOn(CommentResource.class).findById(resultVo.getKey())).withSelfRel());

        return resultVo;
    }

    public void delete(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new NotFoundEntity("Unable to Delete. ID: " + id + " not found. Please enter a valid value!");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new AccessDeniedException("You do not have permission to delete this comment");
        }
        
        User user = (User) authentication.getPrincipal();
        Comment comment = commentRepository.findById(id).orElseThrow(() -> 
            new NotFoundEntity("Comment ID: " + id + " not found. Please enter a valid value!")
        );

        if (!comment.getCreator().getId().equals(user.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this comment");
        }

        commentRepository.deleteById(id);
    }
}
