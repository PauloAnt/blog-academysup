package br.com.paulo.versions.VOsv1;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.paulo.entities.Post;
import br.com.paulo.entities.User;

@JsonPropertyOrder(value = {"id", "creator", "post", "description", "moment"})
public class CommentVO extends RepresentationModel<CommentVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	Long key;
	User creator;
	Post post;
	String description;
	Instant moment;
    
	public CommentVO(Long key, User creator, Post post, String description, Instant moment) {
		this.key = key;
		this.creator = creator;
		this.post = post;
		this.description = description;
		this.moment = moment;
	}
	
	public CommentVO() {}

	public Long getKey() {
		return key;
	}

	public User getCreator() {
		return creator;
	}

	public Post getPost() {
		return post;
	}

	public String getDescription() {
		return description;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setKey(Long key) {
		this.key = key;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public void setPost(Post post) {
		this.post = post;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(key);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentVO other = (CommentVO) obj;
		return Objects.equals(key, other.key);
	}
}
