package com.example.sol

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SolarImage(val name: String, val route: Int)


@Composable
fun GenerateSolarCard(image: SolarImage) {
    var showDropDownMenu by rememberSaveable {
        mutableStateOf(false)
    }

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

            IconButton(onClick = { showDropDownMenu = true }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")

                DropDownMenu(showDropDownMenu){ showDropDownMenu = it }
            }
        }
    }
}

@Composable
fun DropDownMenu(showDropDownMenu: Boolean, onExpandedChange:(Boolean) -> Unit) {

    DropdownMenu(
        expanded = showDropDownMenu,
        onDismissRequest = { onExpandedChange(false) }
    ) {
        DropdownMenuItem(
            text = { Text("Compartir") },
            onClick = { /*TODO*/ },
            leadingIcon = { Icon(imageVector = Icons.Default.Share, contentDescription = "Compartir")}
        )

        DropdownMenuItem(
            text = { Text("Eliminar") },
            onClick = { /*TODO*/ },
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