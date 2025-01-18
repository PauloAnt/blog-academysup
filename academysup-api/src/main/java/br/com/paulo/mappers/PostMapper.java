package br.com.paulo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.paulo.entities.Post;
import br.com.paulo.versions.VOsv1.PostVO;

@Component
public class PostMapper {

    private static ModelMapper mapper = new ModelMapper();
    
    static {
    	mapper.createTypeMap(Post.class, PostVO.class)
    	.addMapping(Post::getId, PostVO::setKey);
    }

    public PostVO convertePostForPostVO(Post post) {
        return mapper.map(post, PostVO.class);
    }

    public Post convertePostVOForPost(PostVO postVo) {
        return mapper.map(postVo, Post.class);
    }
}
