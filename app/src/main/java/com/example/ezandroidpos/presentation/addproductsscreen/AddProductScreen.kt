package com.example.ezandroidpos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ezandroidpos.data.Category
import com.example.ezandroidpos.util.Screen

/*
 * Screen for adding products to the database.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAddScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "Add a Product",
                style = MaterialTheme.typography.headlineSmall,
            )
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                // Focus Manager (Keyboard)
                val focusManager = LocalFocusManager.current

                // State Variables
                var name by remember { mutableStateOf(TextFieldValue()) }
                var price by remember { mutableStateOf(TextFieldValue()) }
                var expanded by remember { mutableStateOf(false) }
                var selectedCat by remember { mutableStateOf("") }


                Spacer(modifier = Modifier.padding(10.dp))

                // Name field
                OutlinedTextField(
                    value = name,
                    label = { Text(text = "Name") },
                    onValueChange = { name = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        })
                )
                Spacer(modifier = Modifier.padding(10.dp))

                // Price field
                OutlinedTextField(
                    value = price,
                    label = { Text(text = "Price") },
                    onValueChange = { price = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,

                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.moveFocus(FocusDirection.Next)
                    })
                )

                Spacer(modifier = Modifier.padding(10.dp))

                // Category Field
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                )
                {
                    OutlinedTextField(
                        value = selectedCat,
                        onValueChange = { selectedCat = it },

                        singleLine = true,
                        label = { Text(text = "Select Category") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "Dropdown",
                                modifier = Modifier.clickable { expanded = true }
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        })
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        Category.values().forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(text = cat.name) },
                                onClick = {
                                    selectedCat = cat.name
                                    expanded = false
                                    focusManager.clearFocus()
                                }
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Row {
                Button(onClick = { navController.navigate(Screen.PointOfSaleScreen.route) }) {
                    Text(text = "Back")
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Button(onClick = { }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

//@Preview (showBackground = true)
//@Composable
//fun ProductAddScreenPreview() {
//    ProductAddScreen(navController = NavController(LocalContext.current))
//}