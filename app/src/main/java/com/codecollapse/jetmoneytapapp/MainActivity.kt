package com.codecollapse.jetmoneytapapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codecollapse.jetmoneytapapp.ui.theme.JetMoneyTapAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetMoneyTapAppTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0xFFE3F6FF)
    ) {
        val moneyTapCounter = remember {
            mutableStateOf(0)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$${moneyTapCounter.value}",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                style = TextStyle(color = Color(0xff0081B4))
            )
            Spacer(modifier = Modifier.height(100.dp))
            CircleShape(moneyTapCounter.value) {
                moneyTapCounter.value = it
            }
            Spacer(modifier = Modifier.height(12.dp))
            if (moneyTapCounter.value > 10) {
                Text(
                    text = "congratulations you earn $10",
                    style = TextStyle(color = Color(0xff10A19D)),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}

@Composable
fun CircleShape(moneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .width(120.dp)
            .padding(12.dp)
            .clickable {
                updateMoneyCounter(moneyCounter + 1)
            }, shape = CircleShape,
        elevation = 4.dp, border = BorderStroke(1.dp, Color(0xff8DCBE6))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Tap", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetMoneyTapAppTheme {
        MainScreen()
    }
}