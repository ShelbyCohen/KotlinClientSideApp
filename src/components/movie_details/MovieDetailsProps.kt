package components.movie_details

import model.Movie
import react.RProps

interface MovieDetailsProps: RProps {
    var current: Movie?
}
