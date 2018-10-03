package services

import kotlinext.js.jsObject
import model.Actor
import services.axios.AxiosConfigSettings
import services.axios.AxiosResponse
import model.Movie
import kotlin.js.Promise
import kotlin.js.json

@JsModule("axios")
external fun <T> axios(config: AxiosConfigSettings): Promise<AxiosResponse<T>>

class MoviesServiceViaWebFlux : MoviesService {
    override fun loadMoviesByActorName(actorName: String, start: Int, limit: Int, handler: (List<Movie>) -> Unit) {
        val url = "byActorName/$actorName/$start/$limit"
        sendQuery(url, handler)
    }

    override fun loadActorsByCountOfFilmsMade(filmLimit: Int, handler: (List<Actor>) -> Unit) {
        val url = "byFilmography/$filmLimit"
        sendQuery(url, handler)
    }

    private fun <T> sendQuery(actorsByNumOfMovies: String, handler: (List<T>) -> Unit) {
        axios<Array<T>>(settings(actorsByNumOfMovies)).then { response ->
            handler(response.data.toList())
        }
    }

    private fun settings(path: String): AxiosConfigSettings {
        return jsObject {
            url = "http://localhost:8080/movies/$path"
            method = "GET"
            headers = json("Accept" to "application/json")
        }
    }
}