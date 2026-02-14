# ProyectoJuego Android – "Play Portal" (Piedra Papel o Tijeras)

Aplicación Android desarrollada en Kotlin con Jetpack Compose.

## Descripción

Play Portal es una aplicación con sistema de login y selección de juegos.
Incluye el juego "Piedra, Papel o Tijeras" y manejo de perfil de usuario.

## 🏗 Arquitectura

El proyecto implementa el patrón **MVVM (Model - View - ViewModel)**:

- **Model** → ResultadoPartida, DatosPrueba
- **ViewModel** → JuegoViewModel, ThemeViewModel
- **View** → PantallaLogin, JuegosDisponiblesScreen, PantallaJuego, PantallaPerfil

## Características

- Modo claro / modo oscuro
- Sistema de navegación manual
- Manejo de estado con remember y mutableStateOf
- UI moderna con Material 3
- Arquitectura desacoplada

## 🛠 Tecnologías usadas

- Kotlin
- Jetpack Compose
- Material 3
- Android Studio

## Cómo ejecutar

1. Clonar el repositorio
2. Abrir en Android Studio
3. Ejecutar en emulador o dispositivo físico

---

Proyecto desarrollado como parte de la Fase 3 – Arquitectura MVVM.
