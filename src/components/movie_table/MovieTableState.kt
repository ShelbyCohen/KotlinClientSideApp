package components.movie_table

import react.RState
import model.Movie

interface MovieTableState: RState {
    var movies: List<Movie>
}
