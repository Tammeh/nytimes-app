package com.tomasm.core.utils

object FiltersProvider {
    val mostItemList = mutableListOf(
        FilterItem("Mas vistos", "mostviewed"),
        FilterItem("Mas compartidos por mail", "mostemailed"),
        FilterItem("Mas compartidos en redes", "mostshared")
    )
    val periodItemList = mutableListOf(
        FilterItem("1 Día", "1"),
        FilterItem("7 Días", "7"),
        FilterItem("30 Días", "30")
    )
}