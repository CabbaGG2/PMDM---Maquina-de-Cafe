package test.kotlin
import main.kotlin.CoffeeMachineState
import main.kotlin.StateMachine
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class CoffeMachineTest2 {

        @BeforeEach
        fun setUp() {
            StateMachine.cleanMachine()
        }

        @Test
        fun `deberia hacer cafe con suficiente dinero`() {
            StateMachine.solicitarPago(2.0)
            assertTrue(StateMachine.currentState is CoffeeMachineState.ServingCoffee)
        }

        @Test
        fun `no deberia hacer cafe con dinero insuficiente`() {
            StateMachine.solicitarPago(0.0)
            assertTrue(StateMachine.currentState is CoffeeMachineState.Idle)
        }

        @Test
        fun `deberia limpiar la maquina y volver a estado idle`() {
            StateMachine.solicitarPago(2.0)
            StateMachine.cleanMachine()
            assertTrue(StateMachine.currentState is CoffeeMachineState.Idle)
        }

        @Test
        fun `cuando tratas de hacer cafe dos veces deberia lanzar un mensaje que ya hay cafe echo`() {
            StateMachine.solicitarPago(2.0)
            StateMachine.solicitarPago(2.0)
            assertTrue(StateMachine.currentState is CoffeeMachineState.ServingCoffee)
        }

        @Test
        fun `si tratas de limpiar la maquina dos veces deberia lanzar un mensaje que no se puede limpiar denuevo en estado Idle`() {
            StateMachine.cleanMachine()
            StateMachine.cleanMachine()
            assertTrue(StateMachine.currentState is CoffeeMachineState.Idle)
        }

}