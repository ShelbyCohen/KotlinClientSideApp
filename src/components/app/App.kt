package components.app

import components.actor_selection.actorSelection
import components.movie_display.movieDisplay
import react.*
import react.dom.*
import services.MoviesServiceViaWebFlux


class App : RComponent<AppProps, AppState>() {
    private val neo4JService = MoviesServiceViaWebFlux()

    init {
        state.currentActorName = ""
    }

    override fun RBuilder.render() {
        div(classes = "container") {
            div(classes = "row") {
                h2(classes = "col-8 text-center") { +"New Movie Database" }
            }
            div(classes = "row mt-3") {
                actorSelection {
                    attrs {
                        service = neo4JService
                        actorChosenHandler = { name ->
                            setState { currentActorName = name }
                        }
                    }
                }
            }
        }
        if (state.currentActorName.isNotEmpty()) {
            movieDisplay {
                attrs {
                    service = neo4JService
                    actorName = state.currentActorName
                }
            }
        }
    }
}

fun RBuilder.app() = child(App::class) {}
