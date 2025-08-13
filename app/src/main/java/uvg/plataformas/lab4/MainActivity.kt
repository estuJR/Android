package uvg.plataformas.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

class Lab4Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Lab4Screen() }
    }
}

@Composable
fun Lab4Screen() {

    val verde = Color(0xFF2E7D32)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            .border(width = 6.dp, color = verde)
            .padding(24.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.uvg),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.7f)
                .alpha(0.06f),
            contentScale = ContentScale.Fit
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 4.1) bloque superior (centrado vertical)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                // título
                Text(
                    text = "Universidad del Valle\nde Guatemala",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))


                Text(
                    text = "Programación de plataformas\nmóviles, Sección 30",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    lineHeight = 22.sp
                )

                Spacer(Modifier.height(20.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(0.86f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "INTEGRANTES",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Column {
                        Text("Javier Castillo", fontSize = 14.sp)
                        Text("Diego Gonzales", fontSize = 14.sp)
                    }
                }

                Spacer(Modifier.height(10.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(0.86f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "CATEDRÁTICO",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Column {
                        Text("Juan Carlos Durini", fontSize = 14.sp)
                    }
                }
            }

            // 4.2) bloque inferior (nombre y carné un poco más arriba)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 28.dp)
            ) {
                Text("Javier Alvizures", textAlign = TextAlign.Center, fontSize = 16.sp)
                Text("24333", textAlign = TextAlign.Center, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Lab4Preview() {
    Lab4Screen()
}