package com.aff.proyectojuegoandreaff.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aff.proyectojuegoandreaff.model.ResultadoPartida
import kotlin.random.Random
import com.aff.proyectojuegoandreaff.model.DatosPrueba

class JuegoViewModel : ViewModel() {

    private val opciones = DatosPrueba.opcionesPPT


    // ✅ Stats
    private val _partidas = mutableStateOf(0)
    val partidas: State<Int> = _partidas

    private val _victorias = mutableStateOf(0)
    val victorias: State<Int> = _victorias

    fun jugar(eleccionUsuario: String): ResultadoPartida {
        val eleccionCPU = opciones[Random.nextInt(opciones.size)]
        val resultado = determinarResultado(eleccionUsuario, eleccionCPU)

        // ✅ Actualizar stats
        _partidas.value++

        if (resultado == "Ganaste") {
            _victorias.value++
        }

        return ResultadoPartida(
            eleccionUsuario = eleccionUsuario,
            eleccionCPU = eleccionCPU,
            resultado = resultado
        )
    }

    private fun determinarResultado(usuario: String, cpu: String): String {
        if (usuario == cpu) return "Empate"

        return when (usuario) {
            "Piedra" -> if (cpu == "Tijera") "Ganaste" else "Perdiste"
            "Papel" -> if (cpu == "Piedra") "Ganaste" else "Perdiste"
            "Tijera" -> if (cpu == "Papel") "Ganaste" else "Perdiste"
            else -> "Elección inválida"
        }
    }
}
