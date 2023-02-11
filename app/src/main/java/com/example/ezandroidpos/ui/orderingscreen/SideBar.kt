@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.ezandroidpos.ui.orderingscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ezandroidpos.R
import com.example.ezandroidpos.data.Item
import com.example.ezandroidpos.ui.PosViewModel
import java.time.LocalDate

@Composable
fun SideBar(
    modifier: Modifier,
    viewModel: PosViewModel = hiltViewModel(),
) {
    val orderState by viewModel.uiOrderState.collectAsState()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {
        OrderPane(
            modifier = Modifier,
            orderId = orderState.orderId,
            orderItems = orderState.orderItems,
            onItemRemove = { viewModel.removeItemFromOrder(it) },
            onClearOrder = { viewModel.clearItemsFromOrder() },
            onCash = { viewModel.onCash(orderState.orderId) },
            onCredit = { viewModel.onCredit(orderState.orderId) },
        )
    }
}

@Composable
fun OrderPane(
    modifier: Modifier,
    orderId: Int,
    orderItems: List<Item>,
    onItemRemove: (Int) -> Unit = {},
    onCash: () -> Unit = {},
    onCredit: () -> Unit = {},
    onClearOrder: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        val scrollState = rememberLazyListState()
        OrderInfo(
            modifier = Modifier,
            orderId = orderId,
        )

        LazyColumn(
            state = scrollState,
            modifier = modifier
                .padding(10.dp)
                .weight(1f)
        ) {
//            Log.d("OrderPane", "OrderPane: $scrollState")

            itemsIndexed(orderItems) { index, item ->
                OrderItem(
                    modifier = Modifier,
                    item = item,
                    onRemove = { onItemRemove(index) },
                    onPriceUpdate = { /* TODO*/ },
                )
            }

        }
        ActionButtons(
            modifier = Modifier
                .padding(4.dp),
            onClearOrderClick = { onClearOrder() },
            onCashClick = { onCash() },
            onCreditClick = { onCredit() },
            orderItems = orderItems,
        )
    }
}

@Composable
fun OrderInfo(
    modifier: Modifier,
    orderId: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Order #$orderId",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(text = "${LocalDate.now()}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderItem(
    modifier: Modifier,
    item: Item,
    onRemove: () -> Unit,
    onPriceUpdate: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .padding(2.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = modifier
                .padding(start = 10.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = item.name,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = item.price.toString(),
                modifier = Modifier
                    .clickable { onPriceUpdate() }
                    .padding(end = 10.dp)
            )

            // add the 'close_20pc' icon from drawables
            IconButton(
                onClick = { onRemove() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    modifier = Modifier
                        .size(20.dp),
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun ActionButtons(
    modifier: Modifier,
    onClearOrderClick: () -> Unit,
    onCashClick: () -> Unit = {},
    onCreditClick: () -> Unit = {},
    orderItems: List<Item> = listOf(),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val orderTotal = orderItems.sumOf { it.price}

        OutlinedButton(
            onClick = { onClearOrderClick() },
            //squared corners
            shape = RoundedCornerShape(4.dp),
        ) {
            Text(text = stringResource(R.string.clear))
        }
        Spacer(
            modifier = modifier
                .weight(1f)
        )
        if (orderTotal != 0.0) {
            Text(
                modifier = modifier
                    .padding(end = 10.dp),
                fontSize = 14.sp,
                text = stringResource(id = R.string.total) + "$orderTotal"
            )
        }

        Button(
            modifier = modifier,
            onClick = { onCashClick() },
            shape = RoundedCornerShape(8.dp),
            ) {
            Text(text = stringResource(R.string.pay_cash))
        }
        OutlinedButton(
            modifier = modifier,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.inversePrimary
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = { onCreditClick() }
        ) {
            Text(
                text = stringResource(R.string.pay_card),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SideBar(modifier = Modifier)
//}