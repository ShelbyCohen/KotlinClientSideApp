package components.movie_display

import react.RState
import model.Movie

interface MovieDisplayState : RState {
    var actorName: String
    var currentMovies: List<Movie>
    var selectedMovie: Movie
    var endOfData: Boolean
    var start: Int
    var limit: Int
}
