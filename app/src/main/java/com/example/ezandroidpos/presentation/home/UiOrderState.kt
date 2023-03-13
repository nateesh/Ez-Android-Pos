package com.example.ezandroidpos.presentation.home

import com.example.ezandroidpos.data.Item

data class UiOrderState(
    val reportId: Int = 1,
    val orderId: Int = 1,
    val orderItems: List<Item> = emptyList(),
)
