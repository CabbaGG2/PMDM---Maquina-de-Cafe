package test.kotlin

import main.kotlin.CoffeeMachine
import main.kotlin.CoffeeMachineState
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName

class CoffeeMachineTest {

    @BeforeEach
    fun setUp() {
        CoffeeMachine.clean()
    }

    @Test
    fun `deberia hacer cafe con suficiente dinero`() {
        CoffeeMachine.solicitarPago(2)
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.ServingCoffee)
    }

    @Test
    fun `no deberia hacer cafe con dinero insuficiente`() {
        CoffeeMachine.solicitarPago(0)
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.Idle)
    }

    @Test
    fun `deberia limpiar la maquina y volver a estado idle`() {
        CoffeeMachine.solicitarPago(2)
        CoffeeMachine.clean()
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.Idle)
    }

    @Test
    fun `cuando tratas de hacer cafe dos veces deberia lanzar un mensaje que ya hay cafe echo`(){
        CoffeeMachine.solicitarPago(2)
        CoffeeMachine.solicitarPago(2)
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.ServingCoffee)
    }

    @Test
    @DisplayName ("Si tratas de pedir otro cafe deberia arrojar un mensaje diciendo que hay un cafe servido que porfavor lo recojas y procede a limpiar")
    fun cafeServidoNoLimpia() {
        CoffeeMachine.solicitarPago(2)
        CoffeeMachine.solicitarPago(2)
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.Idle)
    }
}