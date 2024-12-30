package com.melpiece.personlist

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.melpiece.personlist.ui.theme.PersonListTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddUserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonListTheme {
                AddUserScreen()
            }
        }
    }
}
@Composable
fun AddUserScreen(){
    val context = LocalContext.current
    val db = remember {
        AppDatabase.getDatabase(context)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var telNum by remember { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("이름") }
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("메일") }
        )
        OutlinedTextField(
            value = telNum,
            onValueChange = { telNum = it },
            label = { Text("전화번호") }
        )

        val coroutineScope = rememberCoroutineScope()
        Button(onClick = {
            coroutineScope.launch (Dispatchers.IO){
                db.userDao().insertAll(
                    User(
                        name = name,
                        phone = telNum,
                        email = email,
                    )
                )
                val activity = context as? Activity
                activity?.finish()
            }
        }) {
            Text("등록")
        }


    }

}