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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.sol.ui.theme.SolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolTheme {
                var navDrawerState = rememberDrawerState(DrawerValue.Closed)
                var scope = rememberCoroutineScope()
                var navController = rememberNavController()

                MyApp(navDrawerState)
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(navDrawerState: DrawerState) {
    Scaffold(
        bottomBar = { ToolBar(navDrawerState) }
    ){
        Content()
    }
}

@Composable
fun ToolBar(navDrawerState: DrawerState) {
    BottomAppBar(
        containerColor = Color.Red,
    ){
        IconButton(onClick = { navDrawerState.isOpen }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "LateralMenu")
        }

        NavigationDrawer(navDrawerState)

        FavBadgedBox()

        FAB()
    }
}

@Composable
fun NavigationDrawer(navDrawerState: DrawerState) {

    var screen by rememberSaveable {
        mutableStateOf("")
    }

    ModalNavigationDrawer(
        drawerState = navDrawerState,
        drawerContent = {
            //To-do suelto así o bien metido en un ModalDrawerSheet
            Image(painter = painterResource(id = R.drawable.magnetosfera), contentDescription = "")

            NavigationDrawerItem(
                label = { Text("Build") },
                selected = ,
                onClick = { /*TODO*/ }
            )

            NavigationDrawerItem(
                label = { Text("Info") },
                selected = ,
                onClick = { /*TODO*/ }
            )

            NavigationDrawerItem(
                label = { Text("Email") },
                selected = ,
                onClick = { /*TODO*/ }
            )
        },

    ) {}
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

@Composable
fun Content() {
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
            GenerateSolarCard(image = images[it])
        }
    }
}