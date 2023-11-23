package com.example.sol

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ToolBar(scope: CoroutineScope, navDrawerState: DrawerState) {
    BottomAppBar(
        containerColor = Color.Red,
    ){
        IconButton(onClick = {
            scope.launch {
                navDrawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "LateralMenu")
        }

        FavBadgedBox()

        FAB()
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