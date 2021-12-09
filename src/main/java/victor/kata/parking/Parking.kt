package victor.kata.parking

import org.apache.commons.lang3.NotImplementedException
import kotlin.math.abs

/**
 * Handles the parking mechanisms: park/unpark a car (also for disabled-only bays) and provides a string representation of its state.
 */
class Parking(val size: Int, val pedestrianExits: List<Int>, val disabledBays: List<Int>) {
    private val parked = mutableSetOf<Int>()
    /**
     * @return the number of available parking bays left
     */
    fun getAvailableBays(): Int = size * size - pedestrianExits.size - parked.size

    init {
        require(pedestrianExits.isNotEmpty())
    }

    /**
     * Park the car of the given type ('D' being dedicated to disabled people)
     * in closest -to pedestrian exit- and first (starting from the parking's entrance)
     * available bay. Disabled people can only park on dedicated bays.
     *
     *
     * @param carType
     * the car char representation that has to be parked
     * @return bay index of the parked car, -1 if no applicable bay found
     */
    fun parkCar(carType: Char): Int {
        val bayToParkIn = if (carType == 'D') {
            parkDisabledCar() ?: parkRegularCar()
        } else {
            parkRegularCar()
        } ?: return -1
        parked += bayToParkIn
        return bayToParkIn
    }

    private fun parkDisabledCar(): Int? {
        return (disabledBays-parked).minByOrNull { distToClosestPed(it) }
    }

    private fun parkRegularCar(): Int? {
        val availableBays = allIndexes() - pedestrianExits - parked
        return availableBays.minByOrNull { distToClosestPed(it) }
    }

    private fun allIndexes() = 0..size * size

    private fun distToClosestPed(bayIndex: Int): Int = pedestrianExits.minOf { distance(it, bayIndex) }
    private fun distance(it: Int, bayIndex: Int) = abs(it - bayIndex)

    /**
     * Unpark the car from the given index
     *
     * @param index
     * @return true if a car was parked in the bay, false otherwise
     */
    fun unparkCar(index: Int): Boolean = parked.remove(index)

    /**
     * Print a 2-dimensional representation of the parking with the following rules:
     *
     *  * '=' is a pedestrian exit
     *  * '@' is a disabled-only empty bay
     *  * 'U' is a non-disabled empty bay
     *  * 'D' is a disabled-only occupied bay
     *  * the char representation of a parked vehicle for non-empty bays.
     *
     * U, D, @ and = can be considered as reserved chars.
     *
     * Once an end of lane is reached, then the next lane is reversed (to represent the fact that cars need to turn around)
     *
     * @return the string representation of the parking as a 2-dimensional square. Note that cars do a U turn to continue to the next lane.
     */
    override fun toString(): String {
        throw NotImplementedException("TODO")
    }
}
