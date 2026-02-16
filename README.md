# ProyectoJuego Android – "Play Portal" (Piedra, Papel o Tijeras)

Aplicación Android desarrollada en **Kotlin** con **Jetpack Compose**, integrando consumo de API REST mediante **Retrofit**.

---

#  Descripción

**Play Portal** es una aplicación Android que incluye:

- Sistema de login
- Selección de juegos
- Juego "Piedra, Papel o Tijeras"
- Pantalla de perfil de usuario
- Integración con backend desplegado en Render

La lógica del juego se ejecuta en un **servidor remoto**, y la app consume los resultados mediante una API REST.

---

# Arquitectura

El proyecto implementa el patrón **MVVM (Model - View - ViewModel)** junto con arquitectura cliente-servidor.

## Frontend (Android)
- Jetpack Compose
- ViewModel
- Manejo de estado con mutableStateOf
- Consumo de API con Retrofit

## Backend
- API REST desplegada en Render
- Endpoint GET para procesar la jugada

---

# Integración Backend

## Endpoint utilizado:

GET https://proyectojuego-backend-aff.onrender.com/api/juego/jugar?eleccion={opcion}

## Ejemplo:

/api/juego/jugar?eleccion=piedra

## Respuesta JSON:

```json
{
  "eleccionUsuario": "piedra",
  "eleccionCpu": "tijera",
  "resultado": "Ganaste"
}
