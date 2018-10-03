package components.nav_bar

import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import react.dom.a
import react.dom.li
import react.dom.nav
import react.dom.ul

fun RBuilder.navBar(handler: RHandler<NavBarProps>) = child(NavBar::class, handler)


class NavBar(props: NavBarProps) : RComponent<NavBarProps, RState>(props) {
    override fun RBuilder.render() {
        nav {
            ul(classes = "pagination") {
                li(classes = "page-item") {
                    a(classes = "page-link") {
                        +"Previous"
                        attrs {
                            href = "#"
                            onClickFunction = { props.backwardHandler() }
                        }
                    }
                }
                li(classes = "page-item") {
                    a(classes = "page-link") {
                        +"Next"
                        attrs {
                            href = "#"
                            onClickFunction = { props.forwardHandler() }
                        }
                    }
                }
            }
        }
    }
}