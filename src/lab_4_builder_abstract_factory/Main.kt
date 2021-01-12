package lab_4_builder_abstract_factory

fun main() {
    val factory = PassengerCarFactory()
    val myGreenCar = factory.createGreenCar()

    println(myGreenCar)
}