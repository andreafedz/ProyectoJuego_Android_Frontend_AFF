package com.aff.proyectojuegoandreaff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aff.proyectojuegoandreaff.ui.theme.ProyectoJuegoAndreaFFTheme
import com.aff.proyectojuegoandreaff.view.JuegosDisponiblesScreen
import com.aff.proyectojuegoandreaff.view.PantallaJuego
import com.aff.proyectojuegoandreaff.view.PantallaLogin
import com.aff.proyectojuegoandreaff.view.PantallaPerfil
import com.aff.proyectojuegoandreaff.viewmodel.JuegoViewModel
import com.aff.proyectojuegoandreaff.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {

    private companion object {
        const val RUTA_LOGIN = "login"
        const val RUTA_JUEGOS = "juegos"
        const val RUTA_PERFIL = "perfil"
        const val RUTA_JUEGO_PPT = "juegoPPT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            //  ViewModels globales
            val juegoViewModel: JuegoViewModel = viewModel()
            val themeViewModel: ThemeViewModel = viewModel()

            // Estado global del tema
            val darkTheme by themeViewModel.isDarkMode
            val toggleTheme: () -> Unit = { themeViewModel.toggleTheme() }


            ProyectoJuegoAndreaFFTheme(darkTheme = darkTheme) {


                var pantallaActual by remember { mutableStateOf(RUTA_LOGIN) }

                //  Datos de usuario
                var nombreUsuario by remember { mutableStateOf("AndreaFF") }


                when (pantallaActual) {

                    RUTA_LOGIN -> {
                        PantallaLogin(
                            darkTheme = darkTheme,
                            onToggleTheme = toggleTheme,
                            onIniciarSesion = { user ->
                                if (user.isNotBlank()) nombreUsuario = user
                                pantallaActual = RUTA_JUEGOS
                            },
                            onContinuarInvitado = {
                                nombreUsuario = "Invitado"
                                pantallaActual = RUTA_JUEGOS
                            }
                        )
                    }


                    RUTA_JUEGOS -> {
                        JuegosDisponiblesScreen(
                            darkTheme = darkTheme,
                            onToggleTheme = toggleTheme,
                            onAbrirPPT = { pantallaActual = RUTA_JUEGO_PPT },
                            onAbrirPerfil = { pantallaActual = RUTA_PERFIL }
                        )
                    }

                    RUTA_PERFIL -> {
                        PantallaPerfil(
                            darkTheme = darkTheme,
                            onToggleTheme = toggleTheme,
                            nombreUsuario = nombreUsuario,
                            partidas = juegoViewModel.partidas.value,
                            victorias = juegoViewModel.victorias.value,
                            onEditarNombre = { nuevo ->
                                if (nuevo.isNotBlank()) nombreUsuario = nuevo
                            },
                            onVolver = { pantallaActual = RUTA_JUEGOS },
                            onCerrarSesion = { pantallaActual = RUTA_LOGIN }
                        )
                    }

                    RUTA_JUEGO_PPT -> {
                        PantallaJuego(
                            darkTheme = darkTheme,
                            onToggleTheme = toggleTheme,
                            juegoViewModel = juegoViewModel,
                            onRegresar = { pantallaActual = RUTA_JUEGOS }
                        )
                    }

                    else -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text("Pantalla no encontrada: $pantallaActual")
                        }
                    }
                }
            }
        }
    }
}
