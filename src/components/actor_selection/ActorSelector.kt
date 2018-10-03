package components.actor_selection

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLOptionElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.get
import react.RBuilder
import react.RComponent
import react.RHandler
import react.dom.*
import react.setState

fun RBuilder.actorSelection(handler: RHandler<ActorSelectionProps>) = child(ActorSelection::class, handler)

class ActorSelection(props: ActorSelectionProps) : RComponent<ActorSelectionProps, ActorSelectionState>(props) {
    init {
        state.theActors = emptyList()
        state.count = "50"
    }

    override fun RBuilder.render() {
        form(classes = "col-12") {
            span(classes="mr-2") {
                label(classes = "mr-2") { +"Min number of movies" }
                input(type = InputType.text) {
                    attrs {
                        value = state.count
                        onChangeFunction = { event ->
                            val textBox = event.target as HTMLInputElement
                            setState { count = textBox.value }
                        }
                    }
                }
                input(type = InputType.button) {
                    attrs {
                        value = "Find"
                        onClickFunction = { loadActors(state.count.toInt()) }
                    }
                }
            }
            if (state.theActors.isNotEmpty()) {
                select {
                    state.theActors.map { actor ->
                        option {
                            +actor.name
                            attrs { value = actor.name }
                        }
                    }
                    attrs {
                        onChangeFunction = { event ->
                            val theSelect = event.target as HTMLSelectElement
                            val selectedOption = theSelect[theSelect.selectedIndex] as HTMLOptionElement
                            console.log("Just selected: ", selectedOption.value)
                            props.actorChosenHandler(selectedOption.value)
                        }
                    }
                }
            }
        }
    }
    private fun loadActors(num: Int) {
        props.service.loadActorsByCountOfFilmsMade(num) { data ->
            setState { theActors = data }
        }
    }
}
