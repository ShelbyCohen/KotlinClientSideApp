package components.movie_table

import kotlinx.html.js.onClickFunction
import model.Movie
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RHandler
import react.dom.*
import react.setState

fun RBuilder.movieTable(handler: RHandler<MovieTableProps>) = child(MovieTable::class, handler)

class MovieTable(props: MovieTableProps) : RComponent<MovieTableProps, MovieTableState>(props) {
    init {
        state.movies = props.movies
    }

    override fun componentWillReceiveProps(nextProps: MovieTableProps) {
        state.movies = nextProps.movies
    }

    private fun sortMovies(selector: (Movie) -> String) {
        val sortedMovies = state.movies.sortedBy(selector)
        setState { movies = sortedMovies }
    }
    private fun sortByTitle(e: Event) = sortMovies { it.title }
    private fun sortByGenre(e: Event) = sortMovies { it.genre }

    override fun RBuilder.render() {
        table(classes = "table table-striped table-bordered") {
            thead {
                tr {
                    th {
                        +"Title"
                        span(classes="fas fa-sort ml-2") {}
                        attrs { onClickFunction = ::sortByTitle }
                    }
                    th {
                        +"Genre"
                        span(classes="fas fa-sort ml-2") {}
                        attrs { onClickFunction = ::sortByGenre }
                    }
                    th { +"Tagline" }
                }
            }
            tbody {
                state.movies.map { movie ->
                    tr {
                        td { +movie.title }
                        td { +movie.genre }
                        td { +movie.tagline }
                        attrs {
                            onClickFunction = {
                                props.selectedHandler(movie)
                            }
                        }
                    }
                }
            }
        }
    }
}