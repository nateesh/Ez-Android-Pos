package com.example.ezandroidpos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ezandroidpos.data.Category
import com.example.ezandroidpos.ui.orderingscreen.ProductsGrid
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductsBar(
    modifier: Modifier,
    viewModel: PosViewModel = hiltViewModel(),
) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val category = Category.values().joinToString(", ").split(", ")

    Column(
        modifier = modifier
    ) {
        // Tab Row for Product Categories
        TabRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            category.forEachIndexed { index, category ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                              },
                    modifier = modifier
                        .padding(5.dp),
                ) {
                    Text(text = category)
                }
            }
        }
        // Products Grid
        HorizontalPager(
            count = category.size,
            state = pagerState,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground),
        ) { _ ->
            ProductsGrid(
                modifier = modifier,
                state = pagerState.currentPage,
                viewModel = viewModel,
            )
        }
    }
}

//@Preview
//@Composable
//fun ProductsBarPreview() {
//    ProductsBar(modifier = Modifier, viewModel = hiltViewModel<PosViewModel>())
//}


