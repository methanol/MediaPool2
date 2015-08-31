package de.mediapool.server.entities.product.movies.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NodeEntity
public class ProductMovieNodeDTO extends ProductNodeDTO {

	private static final long serialVersionUID = 1L;

	private int duration;

	private int ageRestriction;

	@JsonIgnore
	@RelatedTo(type = "IS_ON", direction = Direction.INCOMING)
	private @Fetch Set<MovieNodeDTO> movies;

	public void addMovie(MovieNodeDTO movie) {
		if (movies == null) {
			movies = new HashSet<MovieNodeDTO>();
		}
		movies.add(movie);
	}

	@Override
	public String getType() {
		return "PRODUCTMOVIE";
	}

	public ProductMovieNodeDTO(String title, String orginaltitle, int publishedYear, String special, String language, double price, String cover, String description, String ean, String format,
			int duration, int ageRestriction) {
		super(title, orginaltitle, publishedYear, special, language, price, cover, description, ean, format);
		this.duration = duration;
		this.ageRestriction = ageRestriction;

	}

	public ProductMovieNodeDTO() {
		super();

	}

}
