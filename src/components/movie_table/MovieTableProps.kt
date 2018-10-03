package components.movie_table

import react.RProps
import model.Movie

class MovieTableProps(var movies: List<Movie>,
                      var selectedHandler: (Movie) -> Unit) : RProps
