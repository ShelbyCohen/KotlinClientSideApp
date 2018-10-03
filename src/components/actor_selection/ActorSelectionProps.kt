package components.actor_selection

import react.RProps
import services.MoviesService

class ActorSelectionProps(var service: MoviesService,
                          var actorChosenHandler: (String) -> Unit): RProps
