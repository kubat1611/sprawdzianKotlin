package com.example.sprawdziankotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sprawdziankotlin.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                WeatherScreen { message ->
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(onButtonClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF512DA8)), // Fioletowe tło
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Ikona Słońca
        Image(
            painter = painterResource(id = R.drawable.ic_sunny_foreground),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Główna temperatura
        Text(
            text = "5.0°C",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        // Lokalizacja
        Text(
            text = "Warszawa",
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Sekcja prognozy pogody
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4527A0)) // Ciemniejsze tło dla sekcji
                .padding(16.dp)
        ) {
            // Sekcja nagłówkowa: Dzisiaj i Data
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dzisiaj",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "1.12.2024",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            // Divider między nagłówkiem a prognozą
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.White, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Prognoza pogody
            WeatherForecast()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Przycisk
        Button(
            onClick = { onButtonClick("Pogoda będzie piękna!") },
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Sprawdź pozostałe dni",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF512DA8)
@Composable
fun PreviewWeatherScreen() {
    WeatherAppTheme {
        WeatherScreen { /* Do nothing */ }
    }
}

@Composable
fun WeatherForecast() {
    val weatherData = listOf(
        WeatherItem("4.0°C", "8:00", R.drawable.ic_snow_foreground),
        WeatherItem("4.5°C", "10:00", R.drawable.ic_cloudy_foreground),
        WeatherItem("4.7°C", "12:00", R.drawable.ic_sunny_and_cloudy_foreground),
        WeatherItem("5.0°C", "14:00", R.drawable.ic_sunny_foreground),
        WeatherItem("5.0°C", "16:00", R.drawable.ic_sunny_and_cloudy_foreground),
        WeatherItem("4.7°C", "18:00", R.drawable.ic_cloudy_foreground)
    )

    LazyRow(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(Color(0xFF4527A0)), // Ciemniejsze tło
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(weatherData) { item ->
            WeatherForecastItem(item)
        }
    }
}

@Composable
fun WeatherForecastItem(item: WeatherItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        // Temperatura na górze
        Text(
            text = item.temperature,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        // Ikona pośrodku
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(vertical = 4.dp)
        )

        // Czas na dole
        Text(
            text = item.time,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF4527A0)
@Composable
fun PreviewWeatherForecastItem() {
    WeatherAppTheme {
        WeatherForecastItem(
            WeatherItem("4.0°C", "8:00", R.drawable.ic_snow_foreground)
        )
    }
}

data class WeatherItem(val temperature: String, val time: String, val icon: Int)
