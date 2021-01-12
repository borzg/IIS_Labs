package lab_4_builder_abstract_factory

class Foreman(val carBuilder: CarBuilder) {
    fun constructCar(paint: String): Car {
        carBuilder.createCarcass()
        carBuilder.installEngine()
        carBuilder.installWheels()
        carBuilder.paint(paint)
        return carBuilder.build()
    }
}

interface CarBuilder {
    fun createCarcass()
    fun installWheels()
    fun installEngine()
    fun paint(color: String)
    fun build(): Car
}

class PassengerCarBuilder(): CarBuilder {
    var passengerCar = Car()

    override fun createCarcass() { passengerCar.carcass = LightCarcass }

    override fun installWheels() { passengerCar.engine = Engine(LOW_POWER) }

    override fun installEngine() { passengerCar.wheels = Wheels(4, 55) }

    override fun paint(color: String) { passengerCar.color = color }

    override fun build(): Car { return passengerCar }

}

class RaceCarBuilder(): CarBuilder {
    var raceCar = Car()

    override fun createCarcass() { raceCar.carcass = CarbonCarcass }

    override fun installWheels() { raceCar.engine = Engine(HIGH_POWER) }

    override fun installEngine() { raceCar.wheels = Wheels(4, 40) }

    override fun paint(color: String) { raceCar.color = color }

    override fun build(): Car { return raceCar }

}

class TruckBuilder: CarBuilder {
    var truck = Car()

    override fun createCarcass() { truck.carcass = HardCarcass }

    override fun installWheels() { truck.engine = Engine(VERY_HIGH_POWER) }

    override fun installEngine() { truck.wheels = Wheels(6, 150) }

    override fun paint(color: String) { truck.color = color }

    override fun build(): Car { return truck }

}