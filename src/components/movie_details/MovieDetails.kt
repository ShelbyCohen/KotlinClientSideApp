package components.movie_details

import model.Movie
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import react.dom.aside
import react.dom.iframe
import react.dom.p

fun RBuilder.movieDetails(handler: RHandler<MovieDetailsProps>) = child(MovieDetails::class, handler)

class MovieDetails(props: MovieDetailsProps) : RComponent<MovieDetailsProps, RState>(props) {
    override fun RBuilder.render() {
        if (props.current != null) {
            aside { +(props.current?.description ?: "") }

            iframe(classes = "mt-4") {
                attrs {
                    src = convertUrl(props.current)
                }
            }
        } else {
            p { +"No movie selected, click a movie title to see a description" }
        }
    }

    //Movie links are often missing or in obsolete format
    private fun convertUrl(movie: Movie?): String {
        val replacement = "https://www.youtube.com/embed/"
        val originalUrl = movie?.trailer ?: ""
        return originalUrl.replaceBeforeLast("=", replacement)
                .replace("=", "")
    }
}
