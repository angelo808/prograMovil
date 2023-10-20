package pe.edu.ulima.pm20232.aulavirtual.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ResetPasswordScreen(navController: NavHostController){
   Column(){
      Text("Cambio de contraseña")
      Text(text = "Regresar al Login", modifier = Modifier.clickable {
         println("regresar login")
         navController.navigate("login")
      })
   }

}