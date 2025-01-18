//package br.com.paulo.services;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import br.com.paulo.entities.Post;
//import br.com.paulo.mappers.PostMapper;
//import br.com.paulo.repositories.PostRepository;
//import br.com.paulo.test.MockPost;
//import br.com.paulo.versions.VOsv1.PostVO;
//
//@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//class PostServiceTest {
//
//    MockPost input;
//
//    @InjectMocks
//    private PostService service;
//
//    @Mock
//    PostRepository repository;
//
//    @Mock
//    private PostMapper mapper;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        input = new MockPost();
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testFindAll() {
//        List<Post> postsTest = input.mockPostList();
//        List<PostVO> postsVOTest = postsTest.stream()
//            .map(post -> {
//                PostVO postVO = new PostVO();
//                postVO.setKey(post.getId());
//                return postVO;
//            }).toList();
//
//        when(repository.findAll()).thenReturn(postsTest);
//        when(mapper.convertePostForPostVO(postsTest.get(0))).thenReturn(postsVOTest.get(0));
//        when(mapper.convertePostForPostVO(postsTest.get(1))).thenReturn(postsVOTest.get(1));
//        
//        var result = service.findAll();
//        assertNotNull(result);
//        assertTrue(result.size() > 0);
//
//        PostVO entity = result.get(0);
//        assertNotNull(entity);
//        assertNotNull(entity.getKey());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    void testFindById() {
//        Post postTest = input.mockPost1();
//        PostVO postVOTest = new PostVO();
//        postVOTest.setKey(postTest.getId());
//
//        when(repository.findById(1L)).thenReturn(Optional.of(postTest));
//        when(mapper.convertePostForPostVO(postTest)).thenReturn(postVOTest);
//
//        var result = service.findById(1L);
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    void testInsert() {
//        Post postTest = input.mockPost1();
//        PostVO postVOTest = new PostVO();
//        postVOTest.setKey(postTest.getId());
//        
//        doReturn(postTest).when(repository).save(any(Post.class));
//        doReturn(postTest).when(mapper).convertePostVOForPost(any(PostVO.class));
//        doReturn(postVOTest).when(mapper).convertePostForPostVO(any(Post.class));
//
//        var result = service.insert(mapper.convertePostForPostVO(postTest));
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    void testUpdate() {
//        Post postTest = input.mockPost1();
//        PostVO postVOTest = new PostVO();
//        postVOTest.setKey(postTest.getId());
//
//        doReturn(postTest).when(mapper).convertePostVOForPost(postVOTest);
//        doReturn(true).when(repository).existsById(postTest.getId());
//        doReturn(postTest).when(repository).save(postTest);
//        doReturn(postVOTest).when(mapper).convertePostForPostVO(postTest);
//
//        var result = service.update(mapper.convertePostForPostVO(postTest));
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    void testDelete() {
//        Post postTest = input.mockPost1();
//        postTest.setId(1L);
//        
//        doReturn(true).when(repository).existsById(postTest.getId());
//
//        service.delete(1L);
//
//        verify(repository).deleteById(1L);
//    }
//}
