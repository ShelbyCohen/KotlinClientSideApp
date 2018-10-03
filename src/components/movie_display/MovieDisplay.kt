package components.movie_display

import components.movie_details.movieDetails
import components.movie_display.Direction.*
import components.movie_table.movieTable
import components.nav_bar.navBar
import react.RBuilder
import react.RComponent
import react.RHandler
import react.dom.div
import react.setState

fun RBuilder.movieDisplay(handler: RHandler<MovieDisplayProps>) = child(MovieDisplay::class, handler)

class MovieDisplay(props: MovieDisplayProps) : RComponent<MovieDisplayProps, MovieDisplayState>(props) {

    init {
        state.currentMovies = emptyList()
        state.actorName = ""
        state.limit = 10
    }

    override fun RBuilder.render() {
        resetForNewActorIfNecessary()

        div(classes = "container") {
            div(classes = "row mt-3") {
                div(classes = "col-2") {
                    navBar {
                        attrs {
                            backwardHandler = ::loadMoviesBackward
                            forwardHandler = ::loadMoviesForward
                        }
                    }
                }
            }
            div(classes = "row mt-1") {
                div(classes = "col-9") {
                    movieTable {
                        attrs {
                            movies = state.currentMovies
                            selectedHandler = { movie ->
                                setState { selectedMovie = movie }
                            }
                        }
                    }
                }
                div(classes = "col-3") {
                    movieDetails {
                        attrs {
                            current = state.selectedMovie
                        }
                    }
                }
            }
        }
    }

    private fun resetForNewActorIfNecessary() {
        if (state.actorName != props.actorName) {
            state.actorName = props.actorName
            resetSearchCriteria(false, 0)
            loadMovies(FIRST_USE)
        }
    }

    private fun loadMovies(direction: Direction) {
        adjustStartPosition(direction)
        loadMoreMovies()
    }

    private fun loadMoreMovies() {
        props.service.loadMoviesByActorName(
                state.actorName,
                state.start,
                state.limit) { movies ->
            if (movies.isNotEmpty()) {
                setState { currentMovies = movies }
            } else {
                handleEndOfData()
            }
        }
    }

    private fun adjustStartPosition(direction: Direction) {
        when (direction) {
            FORWARD -> {
                if (!state.endOfData) {
                    state.start += state.limit
                }
            }
            BACKWARD -> {
                val oldPosition = state.start - state.limit
                val newStart = if (oldPosition > 0) oldPosition else 0
                resetSearchCriteria(false, newStart)
            }
            FIRST_USE -> resetSearchCriteria(false, 0)
        }
    }

    private fun handleEndOfData() {
        val newStart = state.start - state.limit
        resetSearchCriteria(true, newStart)
    }

    private fun resetSearchCriteria(eod: Boolean, start: Int) {
        state.endOfData = eod
        state.start = start
    }

    private fun loadMoviesForward() = loadMovies(FORWARD)
    private fun loadMoviesBackward() = loadMovies(BACKWARD)
}
