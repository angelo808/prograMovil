package pe.edu.ulima.pm20232.aulavirtual.screens

import android.app.Instrumentation
import android.content.*
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.launch
import pe.edu.ulima.pm20232.aulavirtual.AdminActivity
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.ProfileScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*

@Composable
fun ImageView(url: String, height: Int, width: Int, viewModel: ProfileScreenViewModel) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            // You can apply transformations here if needed
            transformations(CircleCropTransformation())
        }
    )
    Image(
        painter = painter,
        contentDescription = null, // Set a proper content description if required
        modifier = Modifier.size(width.dp, height.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonsGrid(navController: NavController){
    /*
    val pokemonService: PokemonService = PokemonService()
    val pokemons: List<Pokemon> = pokemonService.pokemonList
    var intValue by remember { mutableStateOf(0) }
    LazyVerticalGrid(
        cells = GridCells.Fixed(4) // Specify the number of columns
    ) {
        items(pokemons.size) { i ->
            Image(
                painter = rememberImagePainter(data = pokemons[i].imageUrl),
                contentDescription = pokemons[i].name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 10.dp)
                    .clickable {
                        Log.d("POKEMONS", pokemons[i].id.toString())
                        intValue = pokemons[i].id.toInt()
                        navController.navigate("pokemon/edit?pokemon_id=${intValue}")
                    },
            )
        }
    }
    */
}

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileScreenViewModel){
    val imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/25.png"
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){uri: Uri? ->
        imageUri = uri
    }

    val launcherActivity = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        // Handle the result here
        val resultCode = result.resultCode
        val data = result.data
        // Handle the result as needed
    }

    val launcherImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            Log.d("POKEMON_DETAIL_SCREEN", "onResult")
        }
    )

    if (imageUri == null){
        val uri = Uri.parse(imageUrl)
        val painter = rememberImagePainter(
            data = uri.scheme + "://" + uri.host + uri.path + (if (uri.query != null) uri.query else ""),
            builder = {
                // You can apply transformations here if needed
                transformations(CircleCropTransformation())
            }
        )
        Image(
            painter = painter,
            contentDescription = null, // Set a proper content description if required
            modifier = Modifier.size(200.dp, 200.dp)
        )
    }else{
        imageUri?.let{
            if(Build.VERSION.SDK_INT < 28){
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }else{
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(20.dp)
                )
            }
        }
    }
    Column(){
        Row(){
            Button(
                onClick = {
                    launcher.launch("image/*")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Seleccionar Imagen", fontSize = 16.sp)
            }
            Button(
                onClick = {
                    imageUri= null
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Borrar Imagen", fontSize = 16.sp)
            }
            Button(
                onClick = {
                    // Create an intent to send the image to WhatsApp
                    /*
                    context.startActivity(
                        // on below line we are opening the intent.
                        Intent(
                            // on below line we are calling
                            // uri to parse the data
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                // on below line we are passing uri,
                                // message and whats app phone number.
                                java.lang.String.format(
                                    "https://api.whatsapp.com/send?phone=%s&text=%s",
                                    "993907419",
                                    "Pis oe"
                                )
                            )
                        )
                    )*/

                }
            ) {
                Text("Send Image to WhatsApp")
            }
        }
        Row(){
            val coroutineScope = rememberCoroutineScope()
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp, /*start = 40.dp, end = 40.dp*/),
                onClick = {

                    coroutineScope.launch {
                        val imageLoader = ImageLoader(context)
                        var request = ImageRequest.Builder(context).data(imageUri).build()
                        val bitmap = (imageLoader.execute(request) as SuccessResult).drawable.toBitmap()
                        val uri = context.contentResolver.insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues()
                        )?.apply{
                            context.contentResolver.openOutputStream(this).use{
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                            }
                        }
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "image/jpg"
                        val appPackageName = "com.facebook.katana"
                        val nombre = "XD"
                        intent.putExtra(Intent.EXTRA_TITLE, "Has seleccionado un $nombre")
                        intent.putExtra(Intent.EXTRA_STREAM, uri)
                        intent.putExtra(Intent.EXTRA_TEXT, "Has seleccionado un $nombre")
                        intent.setPackage(appPackageName)
                        launcherImage.launch(intent)
                        if(intent.resolveActivity(context.packageManager) != null){
                            launcherImage.launch(intent)
                        }
                    }
                },
                //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)) ,
            ){
                Text(
                    "Compartir en WhastApp",
                )
            }
        }
        Row(){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp, /*start = 40.dp, end = 40.dp*/),
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "image/jpg"
                    val appPackage = "com.facebook.katana"
                    val nombre = "XD"
                    intent.putExtra(Intent.EXTRA_TITLE, "Has seleccionado un $nombre")
                    intent.putExtra(Intent.EXTRA_TEXT, imageUri)
                    launcherImage.launch(intent)
                    intent.setPackage(appPackage)
                    if(intent.resolveActivity(context.packageManager) != null){
                        launcherImage.launch(intent)
                    }
                },
                //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)) ,
            ){
                Text(
                    "Compartir en Facebook",
                )
            }
        }

        Row(){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp, /*start = 40.dp, end = 40.dp*/),
                onClick = {
                    val intent = Intent(context, AdminActivity::class.java)
                    intent.putExtra("key", "value")
                    launcherActivity.launch(intent)
                },
                //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)) ,
            ){
                Text(
                    "Ir a otro activity",
                )
            }
        }
    }
    /*
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        ImageView(url = imageUrl, width = 50, height = 50, viewModel = viewModel, imageUri)
        Column() {
            Text("Nombre y Apellido")
            Text("Código")
        }
        Button(
            onClick = {

            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Seleccionar Imagen", fontSize = 16.sp)
        }
        Button(
            onClick = {

            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Compartir Imagen", fontSize = 16.sp)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 90.dp)
    ){
        PokemonsGrid(navController)
    }
    */
}