package com.melpiece.personlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.melpiece.personlist.ui.theme.PersonListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonListTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val db = remember {
        AppDatabase.getDatabase(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(horizontal = 10.dp)
    ) {
        Button(onClick = {
            val intent = Intent(context, AddUserActivity::class.java)
            context.startActivity(intent)
        },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)) {
            Text("등록")
        }
        val list by db.userDao().getAll().collectAsStateWithLifecycle(emptyList())
        list.forEach { person ->
            var show by remember { mutableStateOf(false) }
            Column (modifier = Modifier
                .clickable { show = !show }
                .fillMaxWidth()
                .padding(horizontal = 10.dp)){
                Text(person.name.toString())
            }
//            Button(onClick = {
//                show = !show
//            }) {
//                Text(person.name.toString())
//            }
            if (show == true){
                Text("이름: ${person.name}")
                Text("전화번호: ${person.phone}")
                Text("이메일: ${person.email}")
            }
//            Text(person.name.toString(),
//                modifier = Modifier
//                    .clickable {
//                        val intent = Intent(context,PersonActivity::class.java)
//                        context.startActivity(intent)
//                    })
        }


    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PersonListTheme {
        MainScreen()
    }
}