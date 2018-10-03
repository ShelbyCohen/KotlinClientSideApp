package components.actor_selection

import model.Actor
import react.RState

interface ActorSelectionState: RState {
    var theActors: List<Actor>
    var count: String
}
