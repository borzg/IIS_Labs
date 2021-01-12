package lab_5_graph_search_iterator

fun main() {
    val railwayIterator = SmallCountry.createRailwayIterator()
    val onFootIterator = SmallCountry.createOnFootIterator()
    while (railwayIterator.hasNext()) {
        println("Я посетил поездом ${railwayIterator.next().name}")
    }
    println()
    while (onFootIterator.hasNext()) {
        println("Я посетил пешком ${onFootIterator.next().name}")
    }
}

object SmallCountry : Country {

    private val towns: List<Town>
    private val railways: List<Pair<Town, Town>>

    init {

        val Vologda = Town("Вологда")
        val Kostroma = Town("Кострома")
        val Voronezh = Town("Воронеж")
        val Vladivostok = Town("Владивосток")
        val Moskva = Town("Москва")
        val Volgograd = Town("Волгоград")
        val Krasnodar = Town("Краснодар")
        val Vladimir = Town("Владимир")
        towns = listOf(
            Vladivostok,
            Kostroma,
            Vologda,
            Voronezh,
            Krasnodar,
            Vladimir,
            Volgograd,
            Moskva
        )

        railways = listOf(
            Pair(Vladivostok, Kostroma),
            Pair(Vladivostok, Krasnodar),
            Pair(Kostroma, Vologda),
            Pair(Kostroma, Voronezh),
            Pair(Krasnodar, Volgograd),
            Pair(Krasnodar, Vladimir),
            Pair(Volgograd, Moskva)
        )
    }

    override fun createRailwayIterator(): CountryIterator =
        RailwayIterator(towns, railways)

    override fun createOnFootIterator(): CountryIterator =
        OnFootIterator(towns)
}

data class Town(val name: String)

interface Country {
    fun createRailwayIterator(): CountryIterator
    fun createOnFootIterator(): CountryIterator
}

interface CountryIterator {
    fun hasNext(): Boolean
    fun next(): Town
}