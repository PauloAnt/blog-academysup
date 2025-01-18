package br.com.paulo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.paulo.entities.Comment;
import br.com.paulo.versions.VOsv1.CommentVO;

@Component
public class CommentMapper {

    private static ModelMapper mapper = new ModelMapper();
    
    static {
    	mapper.createTypeMap(Comment.class, CommentVO.class)
    	.addMapping(Comment::getId, CommentVO::setKey);
    }

    public CommentVO converteCommentForCommentVO(Comment comment) {
        return mapper.map(comment, CommentVO.class);
    }

    public Comment converteCommentVOForComment(CommentVO commentVo) {
        return mapper.map(commentVo, Comment.class);
    }
}
