package lab_4_builder_abstract_factory

import java.awt.Color

const val LOW_POWER = 100
const val HIGH_POWER = 300
const val VERY_HIGH_POWER = 1000

data class Car(
    var color: String? = null,
    var carcass: Carcass? = null,
    var wheels: Wheels? = null,
    var engine: Engine? = null
)

interface CarFactory {
    fun createGreenCar(): Car
    fun createBlueCar(): Car

}

class PassengerCarFactory: CarFactory {
    val builder = PassengerCarBuilder()
    val foreman = Foreman(builder)

    override fun createGreenCar(): Car {
        return foreman.constructCar("Green")
    }

    override fun createBlueCar(): Car {
        return foreman.constructCar("Blue")
    }
}

class RaceCarFactory: CarFactory {
    val builder = RaceCarBuilder()
    val foreman = Foreman(builder)

    override fun createGreenCar(): Car {
        return foreman.constructCar("Green")
    }

    override fun createBlueCar(): Car {
        return foreman.constructCar("Blue")
    }
}

class TruckCarFactory: CarFactory {
    val builder = TruckBuilder()
    val foreman = Foreman(builder)

    override fun createGreenCar(): Car {
        return foreman.constructCar("Green")
    }

    override fun createBlueCar(): Car {
        return foreman.constructCar("Blue")
    }
}