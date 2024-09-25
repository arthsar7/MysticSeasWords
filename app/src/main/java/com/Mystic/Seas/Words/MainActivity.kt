package com.Mystic.Seas.Words

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.Mystic.Seas.Words.ui.theme.MysticSeasWordsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        SoundManager.init(applicationContext)
        Prefs.init(applicationContext)
        super.onCreate(savedInstanceState)
        setContent {
            MysticSeasWordsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "loading"
                ) {
                    composable("loading") {
                        Loading(onNext = {
                            navController.navigate("menu") {
                                popUpTo("loading") { inclusive = true }
                                launchSingleTop = true
                            }
                        })
                    }
                    composable("menu") {
                        MenuScreen(
                            onNext = {
                                SoundManager.playSound()
                                navController.navigate("game") {
                                    launchSingleTop = true
                                }
                            },
                            onExit = {
                                SoundManager.playSound()
                                navController.navigate("exit") {
                                    launchSingleTop = true
                                }
                            },
                            onOptions = {
                                SoundManager.playSound()
                                navController.navigate("options") {
                                    launchSingleTop = true
                                }
                            })
                    }
                    composable("options") {
                        OptionsScreen(onReturn = {
                            SoundManager.playSound()
                            navController.navigateUp()
                        })
                    }
                    composable("game") {
                        GameScreen {
                            SoundManager.playSound()
                            navController.navigateUp()
                        }
                    }
                    composable("exit") {
                        ExitConfirmationScreen(onReturn = {
                            SoundManager.playSound()
                            navController.navigateUp()
                        })
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        SoundManager.resumeMusic()
    }

    override fun onPause() {
        super.onPause()
        SoundManager.pauseMusic()
    }

    override fun onDestroy() {
        super.onDestroy()
        SoundManager.onDestroy()
    }
}