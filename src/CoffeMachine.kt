object CoffeeMachine {

    var estadoIdle = CoffeeMachineState.Idle()
    var currentState: CoffeeMachineState = estadoIdle

    fun makeCoffee() {
        println("Estado actual: $currentState")

        when (currentState) {
            is CoffeeMachineState.Idle -> {
                println("Máquina encendida. Empezando a hacer café...")
                currentState = CoffeeMachineState.MakingCoffee
                Thread.sleep(2000)
                // Simula un proceso de preparación
                currentState = CoffeeMachineState.ServingCoffee("Nescafé")
                println("¡Café listo! Estado: $currentState")
            }
            is CoffeeMachineState.MakingCoffee -> {
                println("¡Espera! La máquina ya está haciendo café.")
            }
            is CoffeeMachineState.ServingCoffee -> {
                println("Ya hay café servido. Por favor, toma tu café.")
            }
            is CoffeeMachineState.Error -> {
                println("La máquina tiene un error: ${(currentState as CoffeeMachineState.Error).message}")
            }
        }
    }

    fun clean() {
        println("Limpiando la máquina...")
        currentState = estadoIdle
        println("Máquina limpia. Estado: $currentState")
    }

    fun solicitarPago(dinero: Int) {
        println("Contando dinero...")
        if (dinero < 1){
            println("Ay papa.... no es suficiente dinero, ponte las pilas papito y ronca mas duro")
            currentState = estadoIdle
        } else {
            makeCoffee()
        }
    }
}