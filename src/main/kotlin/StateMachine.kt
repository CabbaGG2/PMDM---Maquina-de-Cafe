package main.kotlin

/**
 * Singleton que maneja lo que ocurre en cada estado
 */
object StateMachine {
    public var currentState: CoffeeMachineState = CoffeeMachineState.Idle

    fun setState(newState: CoffeeMachineState) {
        if (isValidTransition(currentState, newState)) {
            currentState = newState
            updateState()
        } else {
            println("Transición inválida de $currentState a $newState")
        }
    }

    private fun isValidTransition(from: CoffeeMachineState, to: CoffeeMachineState): Boolean {
        return when (from) {
            is CoffeeMachineState.Idle -> to == CoffeeMachineState.MakingCoffee || to == CoffeeMachineState.Idle
            is CoffeeMachineState.MakingCoffee -> to == CoffeeMachineState.ServingCoffee
            is CoffeeMachineState.ServingCoffee -> to == CoffeeMachineState.Idle
            is CoffeeMachineState.Error -> to == CoffeeMachineState.Idle
            else -> false
        }
    }

    fun getState(): CoffeeMachineState {
        return currentState
    }

    fun updateState() {
        println("[StateMachine] Estado actual: $currentState")
        currentState.onEnter(this)
    }

    fun solicitarPago(amount: Double) {
        if (currentState is CoffeeMachineState.Idle) {
            if (amount >= 1) {
                println("Pago recibido: $amount euro(s). Preparando café...")
                setState(CoffeeMachineState.MakingCoffee)
            } else {
                println("Pago insuficiente. Por favor, inserte al menos 1 euro.")
            }
        } else {
            println("No se puede aceptar el pago en el estado actual: $currentState, hay que limpiar antes la maquina.")
        }
    }

    fun cleanMachine() {
        if (currentState is CoffeeMachineState.ServingCoffee) {
            println("Limpiando la máquina...")
            currentState = CoffeeMachineState.Idle
            println("Máquina limpia. Estado: $currentState")
        } else {
            println("No se puede limpiar la máquina en el estado actual: $currentState. Debe estar sucia.")
        }
    }
}

/*fun clean() {
    println("Limpiando la máquina...")
    currentState = CoffeeMachineState.Idle
    println("Máquina limpia. Estado: $currentState")
}*/