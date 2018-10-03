package components.nav_bar

import react.RProps

class NavBarProps(var forwardHandler: () -> Unit,
                  var backwardHandler: () -> Unit): RProps
