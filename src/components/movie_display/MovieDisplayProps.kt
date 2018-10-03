package components.movie_display

import react.RProps
import services.MoviesService

interface MovieDisplayProps : RProps {
    var service: MoviesService
    var actorName: String
}
