package com.kidzie.streamview.feature.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kidzie.streamview.R
import com.kidzie.streamview.feature.home.viewmodel.HomeEffect
import com.kidzie.streamview.feature.home.viewmodel.HomeEvent
import com.kidzie.streamview.feature.home.viewmodel.HomeViewModel
import com.kidzie.streamview.feature.home.viewmodel.HomeViewState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(modifier: Modifier) {
    val viewModel : HomeViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value
    val loading = viewModel.loading.collectAsState().value


    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.LoadData)
        viewModel.effect.collect { effect ->
           when (effect) {
               is HomeEffect.NavigateToDetail -> {
                   //TODO : handle navigation to detail
               }
           }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()
        HomeContent(modifier, state)
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .padding(all = 24.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AccountTopView()
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "ic_search"
            )
            Icon(
                painter = painterResource(R.drawable.ic_notification),
                contentDescription = "ic_notification"
            )
        }
    }

}

@Composable
fun AccountTopView() {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(44.dp)
                .clip(_root_ide_package_.androidx.compose.foundation.shape.CircleShape),
            painter = painterResource(R.drawable.profile_1),
            contentDescription = "account"
        )

        Column() {
            Text(text = "Account", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = "Lets Watch Movie", fontSize = 12.sp)
        }
    }
}

@Composable
fun HomeContent(modifier: Modifier, homeState : HomeViewState) {
    Column() {
        MoviePoster(modifier)
    }
}

@Composable
private fun  MoviePoster(modifier: Modifier) {

}


@Preview
@Composable
fun PreviewHomePage() {
    HomePage(Modifier)
}