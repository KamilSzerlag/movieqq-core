package com.moveqq.core.moveqqcore.model.dto.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.moveqq.core.moveqqcore.fault.TmdbClientErrors;
import com.moveqq.core.moveqqcore.fault.TmdbClientException;

import java.util.List;

@JsonDeserialize(builder = Movie.MovieBuilder.class)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Movie {

    private Long id;
    private String title;
    private String overView;
    private String releaseDate;
    private List<String> genres;
    private String originalTitle;
    private String originalLanguage;
    private Integer budget;
    private Double popularity;
    private String posterPath;

    private Movie(MovieBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.overView = builder.overView;
        this.releaseDate = builder.releaseDate;
        this.genres = builder.genres;
        this.originalTitle = builder.originalTitle;
        this.originalLanguage = builder.originalLanguage;
        this.budget = builder.budget;
        this.popularity = builder.popularity;
        this.posterPath = builder.posterPath;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public Integer getBudget() {
        return budget;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
    public static class MovieBuilder {

        private Long id;
        private String title;
        private String overView;
        private String releaseDate;
        private List<String> genres;
        private String originalTitle;
        private String originalLanguage;
        private Integer budget;
        private Double popularity;
        private String posterPath;

        //TODO zmiana typu wyjatku
        public MovieBuilder(Long id, String title) throws TmdbClientException {
            if (id == null)
                throw new TmdbClientException(TmdbClientErrors.MOVIE_DB_BAD_PARAMETERS);
            this.id = id;
            this.title = title;
        }

        public MovieBuilder withOverview(String overView) {
            this.overView = overView;
            return this;
        }

        public MovieBuilder withReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public MovieBuilder withGenres(List<String> genres) {
            this.genres = genres;
            return this;
        }

        public MovieBuilder withOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public MovieBuilder withOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
            return this;
        }

        public MovieBuilder withBudget(Integer budget) {
            this.budget = budget;
            return this;
        }

        public MovieBuilder withPopularity(Double popularity) {
            this.popularity = popularity;
            return this;
        }

        public MovieBuilder withPosterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
