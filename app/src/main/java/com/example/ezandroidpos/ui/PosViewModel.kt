package com.example.ezandroidpos.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ezandroidpos.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PosViewModel() : ViewModel() {

    private val _uiOrderState = MutableStateFlow(UiOrderState())
    val uiOrderState: StateFlow<UiOrderState> = _uiOrderState

    fun addItemToOrder(item: Item) {
        val newItem = item.copy()
        _uiOrderState.update { currentState ->
            currentState.copy(
                orderItems = currentState.orderItems.plus(newItem)
            )
        }
        Log.d("PosViewModel", "Current state: ${_uiOrderState.value.orderItems}")
    }

    fun removeItemFromOrder(index: Int) {

        _uiOrderState.update { currentState ->
            currentState.copy(
                orderItems = currentState.orderItems.minus(currentState.orderItems[index])
            )
        }
        Log.d("PosViewModel", "Order Size: ${_uiOrderState.value.orderItems.size}")
    }

    fun clearItemsFromOrder() {
        Log.d("PosViewModel", "Clearing order")
        _uiOrderState.update { currentState ->
            currentState.copy(
                orderItems = currentState.orderItems.drop(n = currentState.orderItems.size)
            )
        }
        Log.d("PosViewModel", "Order Cleared: ${_uiOrderState.value.orderItems}")
    }

    fun onCash(orderId: Int) {
        if (!orderIsEmpty()) {
            // TODO(Save the order to the database)

            incrementOrder()
            clearItemsFromOrder()
        }
    }

    fun onCredit(orderId: Int) {
        if (!orderIsEmpty()) {
            // TODO(Save the order to the database)

            incrementOrder()
            clearItemsFromOrder()
        }
    }

    // HELPER FUNCTIONS
    // ----------------
    private fun incrementOrder() {
        _uiOrderState.update { currentState ->
            currentState.copy(
                orderId = currentState.orderId.inc()
            )
        }
    }

    private fun orderIsEmpty(): Boolean {
        return _uiOrderState.value.orderItems.isEmpty()
    }
}

