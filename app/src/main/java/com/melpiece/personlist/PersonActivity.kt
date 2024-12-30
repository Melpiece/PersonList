package com.melpiece.personlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.melpiece.personlist.ui.theme.PersonListTheme

class PersonActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonListTheme {
                PersonScreen()
            }
        }
    }
}
@Composable
fun PersonScreen(){
//    Text()

}