# Maquina de café - PMDM

## Diagrama de estados para una máquina expendedora de café

```mermaid
stateDiagram-v2
[*] --> Idle
Idle --> MakingCoffee: solicitarPago (dinero suficiente)
Idle --> Idle: solicitarPago (dinero insuficiente)
MakingCoffee --> ServingCoffee: termina de hacer café
ServingCoffee --> Idle: cleanMachine
MakingCoffee --> MakingCoffee: solicitarPago (ya está haciendo café)
ServingCoffee --> ServingCoffee: solicitarPago (ya hay café servido)
Idle --> Idle: cleanMachine (ya está limpia)