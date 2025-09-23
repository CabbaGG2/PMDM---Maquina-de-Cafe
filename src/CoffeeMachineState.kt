sealed class CoffeeMachineState {
    class Idle : CoffeeMachineState() {
        init {
            println("Buenos días adicto al café, precio del cafe 1 euro: ")
        }
    }
    object MakingCoffee : CoffeeMachineState()
    data class ServingCoffee(val brand: String) : CoffeeMachineState()
    data class Error(val message: String) : CoffeeMachineState()
}

