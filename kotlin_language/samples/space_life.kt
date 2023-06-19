typealias TravelCosts = Map<Planet, Map<Planet, Int>>

enum class SpaceshipType(fuelConsumption: Int) {
    ECONOMY(20),
    COMFORT(50),
    BUSINESS(100)
}

abstract class Spaceship {
    open var mapInfo: TravelCosts? = null
    open var currentPlanet: Planet? = null
    open var fuel: Int = 1000
	open var type: SpaceshipType = SpaceshipType.ECONOMY
    abstract fun travel(planet: Planet)
}

class LowBudjetSpaceship: Spaceship() {
    
    override var fuel = 500
    
    override fun travel(planet: Planet) {
        currentPlanet?.also { sourcePlanet -> 
            mapInfo?.also { spaceMap ->
                val cost = spaceMap[sourcePlanet]?.get(planet) ?: 0
                fuel -= cost
            }
        }
        currentPlanet = planet
        println("Traveled to planet: ${currentPlanet ?: "None"}, fuel remain: $fuel")
    }
    
}

interface Factory<T> {
    fun createInstance(): T
}

//Primary constructor
class Planet constructor(val name: String, val weight: Long, radius: Long) {
    
    //companion
    companion object : Factory<Planet> {
        override fun createInstance(): Planet = Planet("Earth", 10, 10)
    }
    
    //props
    var radius: Long = radius
        // custom accessor
        set(value) {
           println("new radius set $value") 
           // backing field
           field = value
        }
    
    val volume get() = Math.pow(radius.toDouble(), 3.0)
	
	val isInSolarSystem = true
	
    //inits are part of prim construct and evaluated consequently
	init {
		println("Hello! My name is $name")
	}

	var g = 9.8

	init {
		println("my initial g is $g and my weight is $weight")
	}
    
    //lateinits
    
    //hashable conformance
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
 
        other as Planet
 
        if (name != other.name) return false
 
        return true
    }
 
    override fun hashCode(): Int {
        return name.hashCode()
    }
 
    override fun toString(): String {
        return "{$name}"
    }
}

class Star(name: String, weight: Long) {
    
    val weight = weight
    var type: String = "unknown"
    
    // secondary constructor delegates call to primary constructor firstly
    constructor(name: String, weight: Long, type: String) : this(name, weight) {
        this.type = type
    }
}

fun main() {
    val earth = Planet("Earth", 10, 10)
    val jove = Planet("Jove", 100, 100)

    val costMap = buildMap { 
        put(earth, mapOf(jove to 20))
        put(jove, mapOf(earth to 30))
	}
	
    var spacehip1 = LowBudjetSpaceship()
    spacehip1.mapInfo = costMap
    spacehip1.travel(earth)
    spacehip1.travel(jove)
    spacehip1.travel(earth)
    	
    val sun = Star("Sun", 10, "Star")
    sun.type = "Star" 
    println(earth.volume)
    earth.radius = 11
    println(earth.volume)
}