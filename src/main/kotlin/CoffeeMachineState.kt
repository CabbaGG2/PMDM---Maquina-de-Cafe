package main.kotlin
/**
 * Interfaz para definir acciones al entrar en un estado
 */
interface ICoffeeMachineState {
    fun onEnter(machine: StateMachine) {
        // Acción por defecto al entrar en un estado
    }
}

sealed class CoffeeMachineState : ICoffeeMachineState {
    object Idle : CoffeeMachineState() {
        val timestamp: Long = System.currentTimeMillis()
        override fun onEnter(machine: StateMachine) {
            println("[Idle] Entrando en estado Idle a las $timestamp")
            println("[Idle] Máquina lista para hacer café.")
        }
        init {
            println("Buenos días adicto al café, precio del cafe 1 euro: ")
        }
    }

    object MakingCoffee : CoffeeMachineState() {
        override fun onEnter(machine: StateMachine) {
            println("[MakingCoffee] Entrando en estado MakingCoffee")
            println("[MakingCoffee] Haciendo café...")
            Thread.sleep(2000) // Simula un proceso de preparación
            println("[MakingCoffee] Café hecho, sirviendo...")
            machine.setState(ServingCoffee)
        }
    }

    object ServingCoffee : CoffeeMachineState() {
        val timestamp: Long = System.currentTimeMillis()
        override fun onEnter(machine: StateMachine) {
            println("[ServingCoffee] Sirviendo café...")
            Thread.sleep(2000) // Simula el tiempo de servir
            println("[ServingCoffee] Café servido. ¡Disfruta tu café!")
            println("[ServingCoffee] Café servido. ¡Disfruta tu café! (Timestamp: $timestamp)")
        }
    }

    data class Error(val message: String) : CoffeeMachineState() {
        override fun onEnter(machine: StateMachine) {
            TODO("Not yet implemented")
        }
    }
}

