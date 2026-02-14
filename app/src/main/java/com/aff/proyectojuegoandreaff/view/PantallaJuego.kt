package com.aff.proyectojuegoandreaff.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.text.style.TextAlign
import com.aff.proyectojuegoandreaff.R
import com.aff.proyectojuegoandreaff.viewmodel.JuegoViewModel

@Composable
fun OpcionCard(
    titulo: String,
    iconoRes: Int,
    seleccionada: Boolean,
    darkTheme: Boolean,
    onClick: () -> Unit
) {
    // Colores de la tarjeta según tema + selección
    val containerColor = when {
        seleccionada && darkTheme -> Color(0xFF2A2442) // morado oscuro
        seleccionada && !darkTheme -> Color(0xFFFFD7EA) // rosa claro
        darkTheme -> Color(0xFF17122B) // card oscuro normal
        else -> Color(0xFFFFEAF1) // card claro normal
    }

    val textColor = if (darkTheme) Color(0xFFFFE6FF) else Color(0xFF2A2233)

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        modifier = Modifier.width(110.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconoRes),
                contentDescription = titulo,
                modifier = Modifier.size(95.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = titulo,
                style = MaterialTheme.typography.bodyLarge,
                color = textColor
            )
        }
    }
}

@Composable
fun PantallaJuego(
    darkTheme: Boolean,
    onToggleTheme: () -> Unit,
    juegoViewModel: JuegoViewModel,
    onRegresar: () -> Unit
)
 {

    var opcionSeleccionada by remember { mutableStateOf<String?>(null) }

    var eleccionUsuario by remember { mutableStateOf<String?>(null) }
    var eleccionCPU by remember { mutableStateOf<String?>(null) }
    var resultadoFinal by remember { mutableStateOf<String?>(null) }

    val systemDark = isSystemInDarkTheme()
    var darkTheme by remember { mutableStateOf(systemDark) }

    val fondoGradiente = if (darkTheme) {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF0B0814),
                Color(0xFF120C24),
                Color(0xFF0B0814)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFFFFE6F2),
                Color(0xFFFFCFE6),
                Color(0xFFFFE6F2)
            )
        )
    }

    val titleColor = if (darkTheme) Color(0xFFFFE6FF) else MaterialTheme.colorScheme.onBackground
    val subtitleColor = if (darkTheme) Color(0xFFD7CFF0) else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGradiente)
    ) {

        // ✅ FILA SUPERIOR: VOLVER + MODO NOCHE
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 50.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = onRegresar,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (darkTheme) Color(0xFFFFE6FF) else Color(0xFF2A2233)
                )
            ) {
                Text(
                    text = "⬅️  Volver",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            IconButton(onClick = { darkTheme = !darkTheme }) {
                Text(text = if (darkTheme) "☀️" else "🌙")
            }
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Piedra, Papel o Tijeras",
                style = MaterialTheme.typography.headlineMedium,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Elige una opción",
                style = MaterialTheme.typography.bodyLarge,
                color = subtitleColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                OpcionCard(
                    titulo = "Piedra",
                    iconoRes = R.drawable.piedra,
                    seleccionada = opcionSeleccionada == "Piedra",
                    darkTheme = darkTheme
                ) {
                    opcionSeleccionada = "Piedra"
                    val r = juegoViewModel.jugar("Piedra")
                    eleccionUsuario = r.eleccionUsuario
                    eleccionCPU = r.eleccionCPU
                    resultadoFinal = r.resultado
                }

                Spacer(modifier = Modifier.width(12.dp))

                OpcionCard(
                    titulo = "Papel",
                    iconoRes = R.drawable.papel,
                    seleccionada = opcionSeleccionada == "Papel",
                    darkTheme = darkTheme
                ) {
                    opcionSeleccionada = "Papel"
                    val r = juegoViewModel.jugar("Papel")
                    eleccionUsuario = r.eleccionUsuario
                    eleccionCPU = r.eleccionCPU
                    resultadoFinal = r.resultado
                }

                Spacer(modifier = Modifier.width(12.dp))

                OpcionCard(
                    titulo = "Tijeras",
                    iconoRes = R.drawable.tijeras,
                    seleccionada = opcionSeleccionada == "Tijeras",
                    darkTheme = darkTheme
                ) {
                    opcionSeleccionada = "Tijeras"
                    val r = juegoViewModel.jugar("Tijeras")
                    eleccionUsuario = r.eleccionUsuario
                    eleccionCPU = r.eleccionCPU
                    resultadoFinal = r.resultado
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            //  Resultado
            ResultadoPanel(
                eleccionUsuario = eleccionUsuario,
                eleccionCPU = eleccionCPU,
                resultado = resultadoFinal,
                darkTheme = darkTheme,
                onJugarDeNuevo = {
                    opcionSeleccionada = null
                    eleccionUsuario = null
                    eleccionCPU = null
                    resultadoFinal = null
                }
            )
        }
    }
}

@Composable
fun ResultadoPanel(
    eleccionUsuario: String?,
    eleccionCPU: String?,
    resultado: String?,
    darkTheme: Boolean,
    onJugarDeNuevo: () -> Unit
) {
    val cardBg = if (darkTheme) Color(0xFF1B1430) else Color(0xFFFFD7EA)
    val divider = if (darkTheme) Color(0x33FFFFFF) else Color(0x33000000)
    val textMain = if (darkTheme) Color(0xFFFDEEFF) else Color(0xFF3A2A33)
    val textSoft = if (darkTheme) Color(0xCCFDEEFF) else Color(0xCC3A2A33)

    val resultadoTexto = resultado ?: "Elige una opción"
    val usuarioTxt = eleccionUsuario ?: "-"
    val cpuTxt = eleccionCPU ?: "-"

    val pillColor = when (resultado?.lowercase()) {
        "ganaste" -> Color(0xFF7BC96F)
        "perdiste" -> Color(0xFFFF6B7A)
        "empate" -> Color(0xFFB59CFF)
        else -> if (darkTheme) Color(0xFF7A69B8) else Color(0xFFFFA6C6)
    }

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.fillMaxWidth(0.85f)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Resultado",
                color = textMain,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(10.dp))

            // Línea divisoria
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(divider)
            )

            Spacer(Modifier.height(12.dp))

            // Elecciones
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text("Tú elegiste:", color = textSoft, style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(10.dp))
                    Text("Sistema eligió:", color = textSoft, style = MaterialTheme.typography.bodyMedium)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(usuarioTxt, color = textMain, style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(10.dp))
                    Text(cpuTxt, color = textMain, style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(Modifier.height(14.dp))

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = pillColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "¡$resultadoTexto!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(12.dp))

            // Botón jugar de nuevo
            Button(
                onClick = onJugarDeNuevo,
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (darkTheme) Color(0xFF7B5CFF) else Color(0xFFFF5AA5),
                    contentColor = Color.White
                )
            ) {
                Text("Jugar de nuevo")
            }
        }
    }
}
