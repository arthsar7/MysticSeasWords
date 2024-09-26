package com.Mystic.Seas.Words

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                                navController.navigate("game/${Prefs.level}") {
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
                    composable("game/{level}",
                        arguments = listOf(
                            navArgument("level") { type = NavType.IntType }
                        )
                        ) {
                        val level = it.arguments?.getInt("level") ?: 1
                        GameScreen(
                            level = level,
                            onResult = {
                                navController.navigate("result/$it") {
                                    launchSingleTop = true
                                    popUpTo("game") { inclusive = true }
                                }
                            }
                        ) {
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

                    composable("result/{result}",
                        arguments = listOf(
                            navArgument("result") { type = NavType.BoolType }
                        )
                    ) {
                        val result = it.arguments?.getBoolean("result") ?: false
                        GameWinScreen(result = result,
                            onRetry = {
                                SoundManager.playSound()
                                navController.navigate("game/${if (!result) Prefs.level else Prefs.level - 1}") {
                                    launchSingleTop = true
                                    popUpTo("result/$result") { inclusive = true }
                                }
                            },
                            onExit = {
                                SoundManager.playSound()
                                navController.navigateUp()
                            },
                            onNext = {
                                SoundManager.playSound()
                                navController.navigate("game/${Prefs.level}") {
                                    launchSingleTop = true
                                    popUpTo("result/$result") { inclusive = true }
                                }
                            }
                        )
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