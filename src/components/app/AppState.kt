package components.app

import react.RState
import model.Movie

interface AppState : RState {
    var currentActorName: String
}
