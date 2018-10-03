package services

import model.Actor
import model.Movie

interface MoviesService {
    fun loadMoviesByActorName(actorName: String, start: Int, limit: Int, handler : (List<Movie>) -> Unit)
    fun loadActorsByCountOfFilmsMade(filmLimit: Int, handler : (List<Actor>) -> Unit)
}