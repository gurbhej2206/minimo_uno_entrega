# Proyecto VuelosManager V1 y V2 

# (Entrega original/V1):
- Proyecto completamente funcional pero al que le faltan algunos requisitos del enunciado
- Creadas las clases Piloto, Dron y Reserva
- Creada la interfaz (VuelosManager) y su Fachada[Singleton] (VuelosManagerImpl)
- Creada la implementación del servicio REST (VuelosService) y (Main)

La instancia de VuelosManagerImpl nos permite hacer todas las operaciones descritas en el enunciado:
- Añadir un nuevo Piloto
- Añadir un nuevo Dron
- Obtener el listado de drones ordenado descendentemente por horas de vuelo
- Obtener el listado de pilotos ordenado descendentemente por horas de vuelo
- Guardar un dron en un almacén para realizar tareas de
  mantenimiento
- Reparar o realizar tareas de mantenimiento en un dron que está en
  el almacén
- Crear un plan de vuelo
- Obtener el listado de planes de vuelo que han sido asignados a un piloto
- Obtener el listado de planes de vuelo que han sido asignados a un dron

## Errores/Problemas encontrados:
- No es un "error" como tal, pero se interpretó mal el enunciado; lo cual llevó a algunos elementos mal implementados:
  - La clase Reserva tiene ahora atributos *String posIn* y *String posDst*, lo cual es incorrecto porque se pedía un conjunto de posiciones (lat/long). Se debería de haber creado una nueva clase *Coordenada* y tener como atributos *double latitud* y *double longitud*; entonces en *Reserva* tendríamos una *List*<*Coordenada*>

## Cosas pendientes de desarrollar:
- Añadir trazas de log4j  a TODOS los métodos (actualmente faltan algunos, sobretodo a nivel ERROR)
- Contemplar todos las errores que se puedan producir y controlarlos con excepciones (y informar con logger ERROR)


# (Seguna entrega/V2):

## Cambios:
- Creada la clase *Coordenada* y añadida como *List*<*Coordenada*>(trayecto) en la clase *Reserva*
- Modificado el método *addReserva()* con un código más sencillo así como la clase *Reserva*:
  - La clase *Reserva* tiene ahora un método *checkIntervalo()* que devuelve True si el intervalo de tiempo de la reserva se solapa cone el que se pasa como parámetro
  - Ahora *addReserva()* comprueba *checkIntervalo()* contra todas las reservas del piloto y del dron, y solo crea la reserva si no se solapa ninguna
  - El atributo *Date fecha* se ha cambiado por *LocalDateTime fechaIn*
- Añadidas trazas log4j a todos los métodos (INFO, ERROR, CRITICAL)
- Creadas e implementadas cuatro excepciones personalizadas:
  - *DronMantenimientoException*: Cuando intentas mandar un dron ya en mantenimiento al almacén
  - *EmptyAlmacenException*: Cuando intentas reparar un dron del almacén pero no hay ninguno
  - *InvalidIDException*: Cuando intentas poner un ID inválido (null o "")
  - *ReservaIntervaloException*: Cuando intentas añadir una reserva en un intérvalo de tiempo en el cual ya se tiene una
- Añadido un nuevo @Test para comprobar el correcto funcionamiento del método *addReserva()*
