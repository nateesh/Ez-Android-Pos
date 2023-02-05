package com.example.ezandroidpos.ui.orderingscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ezandroidpos.ProductsViewModelFactory
import com.example.ezandroidpos.data.Category
import com.example.ezandroidpos.data.Item
import com.example.ezandroidpos.data.ProductsRepo
import com.example.ezandroidpos.ui.OnSuccess
import com.example.ezandroidpos.ui.PosViewModel
import com.example.ezandroidpos.ui.ProductsViewModel
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun ProductsGrid(
    modifier: Modifier,
    state: Int,
    viewModel: PosViewModel,
    productsViewModel: ProductsViewModel = viewModel(
        factory = ProductsViewModelFactory(ProductsRepo())
    ),
) {
//    val products = ProductsGrid.products[state]


    when (val productsList = productsViewModel
        .productsStateFlow
        .asStateFlow()
        .collectAsState()
        .value) {
        is OnSuccess -> {
            val products = productsList.querySnapshot?.toObjects(Item::class.java)

            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = modifier
//                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                )
            ) {
                val productsFiltered = products?.filter { Category.values().indexOf(it.category) == state }

                if (productsFiltered != null) {
                    items(productsFiltered.size) { index ->
                        ProductCard(
                            product = productsFiltered[index],
                            onClickAddItem = { item ->
                                viewModel.addItemToOrder(item)
                            }
                        )
                    }
                }
            }
        }
        else -> {
            Text(text = "error")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    product: Item,
    onClickAddItem: (Item) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable(onClick = { onClickAddItem(product) })
            .height(100.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = (0.5).dp,
            pressedElevation = 5.dp,
            focusedElevation = 1.dp,
            hoveredElevation = 10.dp,
            disabledElevation = 0.dp,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(text = product.name, fontStyle = MaterialTheme.typography.bodyLarge.fontStyle)
            Divider(
                modifier = Modifier
                    .padding(5.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$${product.price}",
                modifier = Modifier
                    .align(Alignment.End),
            )
        }
    }
}