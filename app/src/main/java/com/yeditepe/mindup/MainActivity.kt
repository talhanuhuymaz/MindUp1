package com.yeditepe.mindup


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.yeditepe.mindup.ui.theme.view.NavigationDrawer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavigationComponent(navController)
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") {
            NavigationDrawer {
            MainPage() } }
    }
}
@Preview
@Composable
fun previewScreen() {
    val navController = rememberNavController()
    LoginScreen(navController)
}