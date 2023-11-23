package com.example.sol

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sol.ui.theme.SolTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolTheme {
                /**
                 * Orden de la app:
                 * Primero, ModalNavigationDrawer con sus params necesarios:
                 *       - remember drawerState
                 *       - navController
                 * Éste luego llama al Scaffold (El Scaffold va dentro!)
                 * El Scaffold tiene dentro el grafo de navegación
                 * En cada pantalla a la que navegamos ya hacemos lo que pide
                 * (Aqui solo se nos pide una de las tres pantallas)
                 */

                var navDrawerState = rememberDrawerState( DrawerValue.Closed )
                var navController = rememberNavController()

                NavigationDrawer(navDrawerState, navController)
            }
        }
    }
}

@Composable
fun NavigationDrawer(navDrawerState: DrawerState, navController: NavHostController) {
    var scope = rememberCoroutineScope()
    var screen by rememberSaveable {
        mutableStateOf("MenuPrincipal")
    }

    ModalNavigationDrawer(
        drawerState = navDrawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painter = painterResource(id = R.drawable.cabecera_solar),
                    contentDescription = "HeaderImage",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Default.Build, contentDescription = "Build") },
                    label = { Text("Build") },
                    selected = screen == "MainPage",
                    onClick = {
                        scope.launch { navDrawerState.close() }
                        screen = "MainPage"
                        navController.navigate(screen)
                    }
                )

                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "Info") },
                    label = { Text("Info") },
                    selected = screen == "InfoPage",
                    onClick = {
                        scope.launch { navDrawerState.close() }
                        screen = "InfoPage"
                        navController.navigate(screen)
                    }
                )

                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
                    label = { Text("Email") },
                    selected = screen == "EmailPage",
                    onClick = {
                        scope.launch { navDrawerState.close() }
                        screen = "EmailPage"
                        navController.navigate(screen)
                    }
                )
            }
        },

        ) {
        AppBody(navDrawerState, scope, navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppBody(navDrawerState: DrawerState, scope: CoroutineScope, navController: NavHostController) {

    var snackBarState = remember {SnackbarHostState()}

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarState)},
        bottomBar = { ToolBar(scope, navDrawerState) },
    ){
        Column (
            Modifier
                .fillMaxSize()
                .padding(
                    bottom = it.calculateBottomPadding()
                )){
            NavHost(navController = navController, startDestination = "MainPage"){
                composable("MainPage"){ Content(scope, snackBarState) }
                composable("InfoPage"){ MenuInfo() }
                composable("EmailPage"){ MenuEmail()}
            }
        }
    }
}