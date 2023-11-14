package com.example.sol

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sol.ui.theme.SolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolTheme {
                MyApp()
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun MyApp() {
    Scaffold(
        bottomBar = { ToolBar() }
    ){
        Content()
    }
}

@Composable
fun ToolBar() {
    BottomAppBar(
        containerColor = Color.Red
    ){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "LateralMenu")
        }

        FavBadgedBox()

        FAB()
    }
}

@Composable
fun FAB() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        var context = LocalContext.current
        Button(
            onClick = { Toast.makeText(context, "Hola!", Toast.LENGTH_SHORT).show() },
            modifier = Modifier.clip(RoundedCornerShape(50.dp)),
        ) {
            Image(painter = painterResource(id = R.drawable.ic_more), contentDescription = "")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavBadgedBox() {
    var count by rememberSaveable { mutableStateOf(0) }
    BadgedBox(badge = { Text(text = "$count") }) {
        IconButton(onClick = { count++ }) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Fav")
        }
    }
}

@Composable
fun Content() {
    val estado = rememberLazyGridState()
    LazyVerticalGrid(
        state = estado,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        val images = getSolarImages()
        items(images.size){
            GenerateSolarCard(image = images[it])
        }
    }
}