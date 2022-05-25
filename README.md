# xmen

Pasos para correr la aplicación

1. Crear la base de datos en (Postgresql) con el nombre xmen. (Usuarios por defecto: postgres, password:1234).
2. La aplicación correrá por defecto en el puerto 8085 (Debe estar desocupado al momento de correr la aplicación)
3. Los dnas son únicos en caso de el dna estar registrado enviará un 403 - forbidden

# Funcionamiento

**************************************************
Metodo POST (/mutant)
**************************************************

- - - - - - - - - - - - - -
Prueba 1
- - - - - - - - - - - - - - 

Request -> { "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] } -> Respuesta -> 200 - OK

- - - - - - - - - - -
Prueba 2
- - - - - - - - - - - 
Request -> { "dna": ["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"] } -> Respuesta -> 403 - Forbidden

**************************************************
Metodo GET (/stats)
**************************************************
Request -> Ninguno

Response -> {
    "count_human_dna": 0,
    "count_mutant_dna": 0,
    "ratio": 0.0
}
