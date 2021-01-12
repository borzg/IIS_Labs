package lab_4_builder_abstract_factory

class Engine(val power: Int)

interface Carcass {
    val material: String
}

class Wheels(
    val quantity: Int,
    val diameter: Int
)

object LightCarcass : Carcass {
    override val material: String = "Алюминий"
}

object HardCarcass : Carcass {
    override val material: String = "Сталь"
}

object CarbonCarcass : Carcass {
    override val material: String = "Углеволокно"
}