package uvg.plataformas.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Lab6Root() }
    }
}

@Composable
private fun Lab6Root() {
    MaterialTheme {
        Scaffold(Modifier.fillMaxSize()) { inner ->
            CounterScreen(
                name = "Javier Estuardo Chacon",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun CounterScreen(
    name: String,
    modifier: Modifier = Modifier
) {
    var count by rememberSaveable { mutableIntStateOf(5) }
    var incs by rememberSaveable { mutableIntStateOf(0) }
    var decs by rememberSaveable { mutableIntStateOf(0) }
    var maxVal by rememberSaveable { mutableIntStateOf(count) }
    var minVal by rememberSaveable { mutableIntStateOf(count) }
    var changes by rememberSaveable { mutableIntStateOf(0) }

    val history = remember { mutableStateListOf<Int>() }

    fun pushHistory(v: Int) { history.add(v) }
    fun applyStats(newValue: Int, wasInc: Boolean) {
        if (wasInc) incs++ else decs++
        if (newValue > maxVal) maxVal = newValue
        if (newValue < minVal) minVal = newValue
        changes++
        pushHistory(newValue)
    }

    Column(modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Title(name)

        HorizontalDivider(color = Color(0xFFE6E6E6))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    val n = count - 1
                    count = n
                    applyStats(n, wasInc = false)
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE7ECF6))
            ) { Icon(Icons.Filled.Remove, contentDescription = "Menos", tint = Color(0xFF3C5B8E)) }

            Text(
                text = count.toString(),
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            IconButton(
                onClick = {
                    val n = count + 1
                    count = n
                    applyStats(n, wasInc = true)
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE7ECF6))
            ) { Icon(Icons.Filled.Add, contentDescription = "Más", tint = Color(0xFF3C5B8E)) }
        }

        HorizontalDivider(color = Color(0xFFE6E6E6))

        StatsCard(incs, decs, maxVal, minVal, changes)

        Text(
            text = "Historial:",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold)
        )

        HistoryGrid(
            values = history.toList(),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp)
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                count = 0
                incs = 0
                decs = 0
                maxVal = 0
                minVal = 0
                changes = 0
                history.clear()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3C5B8E),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
        ) { Text("Reiniciar") }
    }
}

@Composable
private fun Title(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2D2E30)
        )
    )
}

@Composable
private fun StatsCard(
    incs: Int,
    decs: Int,
    maxVal: Int,
    minVal: Int,
    changes: Int
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            StatRow("Total incrementos:", incs)
            StatRow("Total decrementos:", decs)
            StatRow("Valor máximo:", maxVal)
            StatRow("Valor mínimo:", minVal)
            StatRow("Total cambios:", changes)
        }
    }
}

@Composable
private fun StatRow(label: String, value: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF2D2E30)
            )
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
            color = Color(0xFF2D2E30)
        )
    }
}

@Composable
private fun HistoryGrid(
    values: List<Int>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed(values) { index, value ->
            val color = when {
                index == 0 -> Color(0xFF2E7D32)
                value >= values[index - 1] -> Color(0xFF2E7D32)
                else -> Color(0xFFC62828)
            }
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Text(value.toString(), color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewLab6() {
    Lab6Root()
}