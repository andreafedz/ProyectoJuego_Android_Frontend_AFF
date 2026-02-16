package com.aff.proyectojuegoandreaff.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPerfil(
    darkTheme: Boolean,
    onToggleTheme: () -> Unit,
    nombreUsuario: String,
    partidas: Int,
    victorias: Int,
    onEditarNombre: (String) -> Unit,
    onVolver: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var nuevoNombre by remember(nombreUsuario) { mutableStateOf(nombreUsuario) }


    val fondo = if (darkTheme) {
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
                Color(0xFFFFD1E8),
                Color(0xFFFFE6F2),
                Color(0xFFFFD1E8)
            )
        )
    }

    val titleColor = if (darkTheme) Color(0xFFFFE6FF) else Color(0xFF8A2B5B)



    val cardBg = if (darkTheme) Color(0xFF1B1430) else Color(0xFFFFCFE3)
    val cardInner = if (darkTheme) Color(0xFF2A2442) else Color(0xFFFFEAF1)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(16.dp)
    ) {

        // Barra superior: Volver + modo noche
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onVolver) {
                Text("⬅️  Volver", color = titleColor, fontWeight = FontWeight.SemiBold)
            }

            IconButton(onClick = onToggleTheme) {
                Text(if (darkTheme) "☀️" else "🌙")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "Mi Perfil",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Avatar
            Surface(
                shape = CircleShape,
                color = Color.White.copy(alpha = if (darkTheme) 0.12f else 0.55f),
                modifier = Modifier.size(96.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("🧑‍🎮", style = MaterialTheme.typography.headlineMedium)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Nombre
            Text(
                text = nombreUsuario,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Stats
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    StatRow(
                        emoji = "⭐",
                        label = "Partidas jugadas:",
                        value = partidas.toString(),
                        darkTheme = darkTheme,
                        innerColor = cardInner
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    StatRow(
                        emoji = "🏆",
                        label = "Victorias:",
                        value = victorias.toString(),
                        darkTheme = darkTheme,
                        innerColor = cardInner
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón editar nombre
            Button(
                onClick = {
                    nuevoNombre = nombreUsuario
                    showDialog = true
                },
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFB24A),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text("✏️  Editar nombre")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Cerrar sesión
            Button(
                onClick = onCerrarSesion,
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5AA5),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text("⏻  Cerrar sesión")
            }
        }
    }

    // Dialog editar nombre
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Editar nombre") },
            text = {
                OutlinedTextField(
                    value = nuevoNombre,
                    onValueChange = { nuevoNombre = it },
                    singleLine = true,
                    label = { Text("Nuevo nombre") }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onEditarNombre(nuevoNombre.trim())
                        showDialog = false
                    }
                ) { Text("Guardar") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
            }
        )
    }
}

@Composable
private fun StatRow(
    emoji: String,
    label: String,
    value: String,
    darkTheme: Boolean,
    innerColor: Color
) {
    val textMain = if (darkTheme) Color(0xFFFDEEFF) else Color(0xFF8A2B5B)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = innerColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, modifier = Modifier.width(28.dp))
            Text(label, color = textMain, modifier = Modifier.weight(1f))
            Text(
                value,
                color = textMain,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
        }
    }
}
