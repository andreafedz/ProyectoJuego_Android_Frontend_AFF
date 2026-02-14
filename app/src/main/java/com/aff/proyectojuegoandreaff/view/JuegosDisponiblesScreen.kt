package com.aff.proyectojuegoandreaff.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun JuegosDisponiblesScreen(
    darkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onAbrirPPT: () -> Unit,
    onAbrirPerfil: () -> Unit
)

{
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    val fondo = if (darkTheme) {
        Brush.verticalGradient(
            listOf(Color(0xFF0B0814), Color(0xFF120C24), Color(0xFF0B0814))
        )
    } else {
        Brush.verticalGradient(
            listOf(Color(0xFFFFD1E8), Color(0xFFFFE6F2), Color(0xFFFFD1E8))
        )
    }


    val titleColor = if (darkTheme) Color(0xFFFFE6FF) else Color(0xFF8A2B5B)

    // Colores de cards según tema (cute pero legibles)
    val cardRosa = if (darkTheme) Color(0xFF1B1430) else Color(0xFFFFC1DC)
    val cardDurazno = if (darkTheme) Color(0xFF2A2442) else Color(0xFFFFD9A6)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fondo)
                .padding(paddingValues)
        ) {

            // Barra superior: modo noche + perfil
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 50.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onToggleTheme) {
                    Text(if (darkTheme) "☀️" else "🌙")
                }

                IconButton(onClick = onAbrirPerfil) {
                    Text(text = "👤")
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(92.dp))

                Text(
                    text = "Juegos\nDisponibles",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = titleColor
                )

                Spacer(modifier = Modifier.height(18.dp))

                JuegoCard(
                    titulo = "Lotería",
                    descripcion = "Genera números aleatorios",
                    emoji = "🎲",
                    colorCard = cardRosa,
                    darkTheme = darkTheme,
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Lotería: próximamente ✨")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                JuegoCard(
                    titulo = "Adivina el número",
                    descripcion = "Intenta adivinar el número secreto",
                    emoji = "💡",
                    colorCard = cardDurazno,
                    darkTheme = darkTheme,
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Adivina el número: próximamente ✨")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                JuegoCard(
                    titulo = "Piedra, papel o tijeras",
                    descripcion = "Elige una opción y juega contra el sistema.",
                    emoji = "✌️",
                    colorCard = cardRosa,
                    darkTheme = darkTheme,
                    onClick = onAbrirPPT
                )
            }
        }
    }
}

@Composable
private fun JuegoCard(
    titulo: String,
    descripcion: String,
    emoji: String,
    colorCard: Color,
    darkTheme: Boolean,
    onClick: () -> Unit
) {
    val textMain = if (darkTheme) Color(0xFFFDEEFF) else Color(0xFF8A2B5B)
    val textSoft = if (darkTheme) Color(0xCCFDEEFF) else Color(0xFF8A2B5B).copy(alpha = 0.9f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 92.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = colorCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White.copy(alpha = if (darkTheme) 0.12f else 0.55f)
            ) {
                Text(
                    text = emoji,
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = textMain
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textSoft
                )
            }
        }
    }
}
