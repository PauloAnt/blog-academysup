package br.com.paulo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.dto.CommentDTO;
import br.com.paulo.services.CommentService;
import br.com.paulo.versions.VOsv1.CommentVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/comment")
@Tag(name = "Comment", description = "Endpoints for Managing Comment")
public class CommentResource {

	@Autowired
	CommentService service;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Finds all Comments", description = "Finds all Comments", tags = {"Comment"},
		responses = {
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = {@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = CommentVO.class))
								)}
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
			
	)
	public ResponseEntity<List<CommentVO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/{id}", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Find by ID Comment", description = "Find by ID Comment", tags = {"Comment"},
		responses = {
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = {@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = CommentVO.class))
								)}
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
			
	)
	public ResponseEntity<CommentVO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Insert Comment", description = "Insert Comment", tags = {"Comment"},
		responses = {
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = {@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = CommentVO.class))
								)}
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
			
	)
	public ResponseEntity<CommentVO> insert(@RequestBody CommentDTO commentDTO) {
		return ResponseEntity.ok().body(service.insert(commentDTO));
	}
	
	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Update Comment", description = "Update Comment", tags = {"Comment"},
		responses = {
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = {@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = CommentVO.class))
								)}
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
			
	)
	public ResponseEntity<CommentVO> update(@RequestBody CommentDTO commentDTO) {
		return ResponseEntity.ok().body(service.update(commentDTO));
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete Comment", description = "Delete Comment", tags = {"Comment"},
		responses = {
				@ApiResponse(description = "No Content", responseCode = "204", 
						content = {@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = CommentVO.class))
								)}
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
			
	)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
