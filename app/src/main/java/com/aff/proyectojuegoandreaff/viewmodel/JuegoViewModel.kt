package com.aff.proyectojuegoandreaff.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aff.proyectojuegoandreaff.model.DatosPrueba
import com.aff.proyectojuegoandreaff.model.ResultadoPartida
import com.aff.proyectojuegoandreaff.network.RetrofitClient
import kotlinx.coroutines.launch

class JuegoViewModel : ViewModel() {


    private val opciones = DatosPrueba.opcionesPPT

    // Stats
    private val _partidas = mutableStateOf(0)
    val partidas: State<Int> = _partidas

    private val _victorias = mutableStateOf(0)
    val victorias: State<Int> = _victorias

    // Resultado recibido del backend
    private val _resultadoPartida = mutableStateOf<ResultadoPartida?>(null)
    val resultadoPartida: State<ResultadoPartida?> = _resultadoPartida


    private val _cargando = mutableStateOf(false)
    val cargando: State<Boolean> = _cargando

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    /**
     * Llamar al backend:
     * GET /api/juego/jugar?eleccion=piedra|papel|tijera
     */
    fun jugar(eleccionUsuario: String) {
        val eleccionNormalizada = eleccionUsuario.trim().lowercase()

        viewModelScope.launch {
            _cargando.value = true
            _error.value = null

            try {
                val resp = RetrofitClient.api.jugar(eleccionNormalizada)

                _resultadoPartida.value = resp

                // actualizar stats con base en respuesta
                _partidas.value = _partidas.value + 1
                if (resp.resultado.equals("Ganaste", ignoreCase = true)) {
                    _victorias.value = _victorias.value + 1
                }

            } catch (e: Exception) {
                _error.value = "Error al conectar con el backend: ${e.message}"
            } finally {
                _cargando.value = false
            }
        }
    }

    fun resetResultado() {
        _resultadoPartida.value = null
        _error.value = null
        _cargando.value = false
    }
}
