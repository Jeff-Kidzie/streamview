package com.kidzie.streamview.feature.home.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kidzie.streamview.R
import com.kidzie.streamview.ui.theme.Primary

data class NavBarItem(
    @DrawableRes val resId: Int,
    val title: String,
)

@Composable
fun ContainerHomePage(modifier: Modifier) {
    val navController = rememberNavController()
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(selectedIndex) { index ->
                selectedIndex = index
                navController.navigate(
                    when (index) {
                        0 -> "home"
                        1 -> "search"
                        2 -> "download"
                        else -> "account"
                    }
                ) {
                    popUpTo("home") { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomePage(modifier) }
            composable("search") { SearchPage(modifier) }
            composable("download") { DownloadPage(modifier) }
            composable("account") { AccountPage(modifier) }
        }
    }
}

@Composable
fun BottomNavBar(selectedIndex: Int, onSelectIndex: (Int) -> Unit) {

    val navBarItem = listOf(
        NavBarItem(R.drawable.ic_home, title = "Home"),
        NavBarItem(R.drawable.ic_search, title = "Search"),
        NavBarItem(R.drawable.ic_download, title = "Downloads"),
        NavBarItem(R.drawable.ic_account, title = "Profile"),
    )
    NavigationBar(
        containerColor = Color.White, tonalElevation = 8.dp
    ) {
        navBarItem.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(item.resId),
                        contentDescription = item.title,
                        tint = if (selectedIndex == index) Primary else Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .size(width = 24.dp, height = 2.dp)
                            .background(if (selectedIndex == index) Primary else Color.Transparent)
                    )

                }
            },
                selected = selectedIndex == index,
                onClick = { onSelectIndex(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    indicatorColor = Color.Transparent,
                )
            )
        }

    }
}