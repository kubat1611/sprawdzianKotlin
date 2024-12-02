package com.example.sprawdziankotlin.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        // Możesz dodać niestandardowe kolory, typografię i inne style tutaj
        content = content
    )
}
