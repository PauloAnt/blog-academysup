package br.com.paulo.versions.VOsv1;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.paulo.entities.Comment;
import br.com.paulo.entities.User;

@JsonPropertyOrder(value = {"id", "creator", "moment", "title", "description"})
public class PostVO extends RepresentationModel<PostVO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "id")
	Long key;

	User creator;
	
	Instant moment;
	String title;
	String description;

	public PostVO(Long key, User creator, Instant moment, String title, String description, List<Comment> comments) {
		this.key = key;
		this.creator = creator;
		this.moment = moment;
		this.title = title;
		this.description = description;
	}

	public PostVO() {}
	
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
		PostVO other = (PostVO) obj;
		return Objects.equals(key, other.key);
	}

	public Long getKey() {
		return key;
	}

	public User getCreator() {
		return creator;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	} 
	
	

}

