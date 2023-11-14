package com.example.sol

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayMainPage() {
    Scaffold (
        topBar = { BottomBar() },
        content = { it }
    )
}


@Composable
fun BottomBar() {
    BottomAppBar {
        Text(text = "hola")
    }
}

@Composable
fun GenerateSolarCard(image: SolarImage) {
    Card(
        Modifier.height(250.dp)
    )
    {
        Image(
            painter = painterResource(id = image.route),
            contentDescription = image.name,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp),
            contentScale = ContentScale.Crop
        )

        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ){
            var state  by rememberSaveable { mutableStateOf(false) }
            Text(
                text = image.name,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")
            }
        }
    }
}

fun getSolarImages(): List<SolarImage>{
    return listOf(
        SolarImage("Corona Solar", R.drawable.corona_solar),
        SolarImage("Erupción Solar", R.drawable.erupcionsolar),
        SolarImage("Espículas", R.drawable.espiculas),
        SolarImage("Filamentos", R.drawable.filamentos),
        SolarImage("Magnetosfera", R.drawable.magnetosfera),
        SolarImage("Mancha Solar", R.drawable.manchasolar),
    )
}

data class SolarImage(val name: String, val route: Int)