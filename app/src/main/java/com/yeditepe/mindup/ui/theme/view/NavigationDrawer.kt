package com.yeditepe.mindup.ui.theme.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yeditepe.mindup.DrawerItems
import com.yeditepe.mindup.MyAppTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.yeditepe.mindup.MainPage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(function: @Composable () -> Unit) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    var selectedItemIndex by remember { mutableStateOf(0) }

    val items = listOf(
        DrawerItems("Home", Icons.Filled.Home),
        DrawerItems("Profile", Icons.Filled.Person),
        DrawerItems("Settings", Icons.Filled.Settings)
    )

    val screenTitle = when (selectedItemIndex) {
        0 -> "Home"
        1 -> "Profile"
        2 -> "Settings"
        else -> "Home"
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Mail",
                        modifier = Modifier.padding(10.dp)
                    )

                    items.forEachIndexed { index, drawerItems ->
                        NavigationDrawerItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                    val route = when (selectedItemIndex) {
                                        0 -> "Home"
                                        1 -> "Profile"
                                        2 -> "Settings"
                                        else -> "Home"
                                    }
                                    navController.navigate(route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = drawerItems.icon,
                                    contentDescription = null,
                                    modifier = Modifier.padding(10.dp)
                                )
                            },
                            label = {
                                Text(text = drawerItems.title)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = screenTitle,
                            modifier = Modifier.padding(10.dp),
                            color = MaterialTheme.colorScheme.background
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.tertiary),
                    navigationIcon = {
                        OutlinedButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            shape = MaterialTheme.shapes.extraSmall
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = null,
                                tint = Color.LightGray
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = "Home",
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable("Home") { MainPage() }
                    composable("Profile") { Screen2() }
                    composable("Settings") { Screen3() }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerPreview() {
    MyAppTheme {
        NavigationDrawer {
            MainPage()
        }
    }
}
