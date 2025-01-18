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
//import br.com.paulo.entities.Comment;
//import br.com.paulo.mappers.CommentMapper;
//import br.com.paulo.repositories.CommentRepository;
//import br.com.paulo.test.MockComment;
//import br.com.paulo.versions.VOsv1.CommentVO;
//
//@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//class CommentServiceTest {
//
//    MockComment input;
//
//    @InjectMocks
//    private CommentService service;
//
//    @Mock
//    CommentRepository repository;
//
//    @Mock
//    private CommentMapper mapper;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        input = new MockComment();
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testFindAll() {
//        List<Comment> commentsTest = input.mockCommentList();
//        List<CommentVO> commentsVOTest = commentsTest.stream()
//            .map(comment -> {
//                CommentVO commentVO = new CommentVO();
//                commentVO.setKey(comment.getId());
//                return commentVO;
//            }).toList();
//
//        when(repository.findAll()).thenReturn(commentsTest);
//        when(mapper.converteCommentForCommentVO(commentsTest.get(0))).thenReturn(commentsVOTest.get(0));
//        when(mapper.converteCommentForCommentVO(commentsTest.get(1))).thenReturn(commentsVOTest.get(1));
//        
//        var result = service.findAll();
//        assertNotNull(result);
//        assertTrue(result.size() > 0);
//
//        CommentVO entity = result.get(0);
//        assertNotNull(entity);
//        assertNotNull(entity.getKey());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    void testFindById() {
//        Comment commentTest = input.mockComment1();
//        CommentVO commentVOTest = new CommentVO();
//        commentVOTest.setKey(commentTest.getId());
//
//        when(repository.findById(1L)).thenReturn(Optional.of(commentTest));
//        when(mapper.converteCommentForCommentVO(commentTest)).thenReturn(commentVOTest);
//
//        var result = service.findById(1L);
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    void testInsert() {
//        Comment commentTest = input.mockComment1();
//        CommentVO commentVOTest = new CommentVO();
//        commentVOTest.setKey(commentTest.getId());
//        
//        doReturn(commentTest).when(repository).save(any(Comment.class));
//        doReturn(commentTest).when(mapper).converteCommentVOForComment(any(CommentVO.class));
//        doReturn(commentVOTest).when(mapper).converteCommentForCommentVO(any(Comment.class));
//
//        var result = service.insert(mapper.converteCommentForCommentVO(commentTest));
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    void testUpdate() {
//        Comment commentTest = input.mockComment1();
//        CommentVO commentVOTest = new CommentVO();
//        commentVOTest.setKey(commentTest.getId());
//
//        doReturn(commentTest).when(mapper).converteCommentVOForComment(commentVOTest);
//        doReturn(true).when(repository).existsById(commentTest.getId());
//        doReturn(commentTest).when(repository).save(commentTest);
//        doReturn(commentVOTest).when(mapper).converteCommentForCommentVO(commentTest);
//       
//
//        var result = service.update(mapper.converteCommentForCommentVO(commentTest));
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    void testDelete() {
//        Comment commentTest = input.mockComment1();
//        commentTest.setId(1L);
//
//        doReturn(true).when(repository).existsById(commentTest.getId());
//
//        service.delete(1L);
//
//        verify(repository).deleteById(1L);
//    }
//}
