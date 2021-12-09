package victor.kata.parking

/**
 * Builder class to get a parking instance
 */
class ParkingBuilder {
    private var size: Int = 0
    private val pedestrianExists = mutableListOf<Int>()
    private val disabledBays = mutableListOf<Int>()


    fun withSquareSize(size: Int): ParkingBuilder {
        this.size=size;
        return this;
    }

    fun withPedestrianExit(pedestrianExitIndex: Int): ParkingBuilder {
        this.pedestrianExists += pedestrianExitIndex
        return this;
    }

    fun withDisabledBay(disabledBayIndex: Int): ParkingBuilder {
        this.disabledBays += disabledBayIndex
        return this;

    }

    fun build(): Parking {
        return Parking(size, pedestrianExists, disabledBays);
    }
}
