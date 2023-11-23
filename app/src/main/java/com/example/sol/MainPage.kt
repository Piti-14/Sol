package com.example.sol

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class SolarImage(val name: String, val route: Int)

@Composable
fun Content(scope: CoroutineScope, snackBarState: SnackbarHostState) {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        val images = getSolarImages()
        items(images.size){
            GenerateSolarCard(image = images[it], scope, snackBarState)
        }
    }
}

@Composable
fun GenerateSolarCard(image: SolarImage, scope: CoroutineScope, snackBarState: SnackbarHostState) {
    var showDropDownMenu by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        Modifier.height(250.dp)
            .clickable {
                scope.launch { snackBarState.showSnackbar(image.name, duration = SnackbarDuration.Short) }
            }
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
            //var state  by rememberSaveable { mutableStateOf(false) }
            Text(
                text = image.name,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            )

            IconButton(onClick = { showDropDownMenu = true }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")

                DropDownMenu(showDropDownMenu){ showDropDownMenu = it }
            }
        }
    }
}

@Composable
fun DropDownMenu(showDropDownMenu: Boolean, onExpandedChange:(Boolean) -> Unit) {
    var context = LocalContext.current
    DropdownMenu(
        expanded = showDropDownMenu,
        onDismissRequest = { onExpandedChange(false) }
    ) {
        DropdownMenuItem(
            text = { Text("Compartir") },
            onClick = { Toast.makeText(context, "Not implemented yet..", Toast.LENGTH_SHORT).show() },
            leadingIcon = { Icon(imageVector = Icons.Default.Share, contentDescription = "Compartir")}
        )

        DropdownMenuItem(
            text = { Text("Eliminar") },
            onClick = { Toast.makeText(context, "Not implemented yet..", Toast.LENGTH_SHORT).show() },
            leadingIcon = { Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")}
        )
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