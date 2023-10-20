package pe.edu.ulima.pm20232.aulavirtual

import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import pe.edu.ulima.pm20232.aulavirtual.components.BottomNavigationBar
import pe.edu.ulima.pm20232.aulavirtual.components.TopNavigationBar
import pe.edu.ulima.pm20232.aulavirtual.configs.BottomBarScreen
import pe.edu.ulima.pm20232.aulavirtual.configs.TopBarScreen
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.*
import pe.edu.ulima.pm20232.aulavirtual.screens.*
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.AulaVirtualTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import android.content.Intent
import android.content.ActivityNotFoundException

class MainActivity() : ComponentActivity(), Parcelable {
    private val loginScrennViewModel by viewModels<LoginScreenViewModel>()
    private val profileScrennViewModel by viewModels<ProfileScreenViewModel>()
    private val homeScrennViewModel by viewModels<HomeScreenViewModel>()
    private val pokemonDetailScrennViewModel by viewModels<PokemonDetailScreenViewModel>()

    private fun shareOnWhatsAppOrFacebook(link: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "¡Echa un vistazo a este enlace: $link")

        try {
            // Verifica si WhatsApp está instalado, si no, intenta abrir Facebook
            intent.`package` = "com.whatsapp"
            startActivity(intent)
        } catch (whatsAppException: ActivityNotFoundException) {
            intent.`package` = "com.facebook.katana"
            try {
                startActivity(intent)
            } catch (facebookException: ActivityNotFoundException) {
                // Maneja la excepción si ninguna de las aplicaciones está instalada
                // Puedes sugerir al usuario que instale WhatsApp o Facebook en este punto.
            }
        }
    }

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AulaVirtualTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val blackList: List<String> = listOf("profile", "login")
                    val currentRoute = navBackStackEntry?.destination?.route
                    var showDialog by remember { mutableStateOf(true) }
                    Scaffold(
                        topBar = {
                            if(blackList.contains(currentRoute) == false) {
                                val screens: List<TopBarScreen> = listOf(
                                    TopBarScreen(
                                        route = "home",
                                        title = "Home",
                                    ),
                                    TopBarScreen(
                                        route = "profile",
                                        title = "Ver Perfíl",
                                    ),
                                    TopBarScreen(
                                        route = "pokemon",
                                        title = "Acerca de",
                                    ),
                                    TopBarScreen(
                                        route = "sign_out",
                                        title = "Cerrar Sesión",
                                    ),
                                )
                                TopNavigationBar(navController, screens)
                            }
                        },
                        bottomBar = {
                            if(blackList.contains(currentRoute) == false) {
                                val screens: List<BottomBarScreen> = listOf(
                                    BottomBarScreen(
                                        route = "home",
                                        title = "Home",
                                        icon = Icons.Default.Home
                                    ),
                                    BottomBarScreen(
                                        route = "profile",
                                        title = "Profile",
                                        icon = Icons.Default.Person
                                    ),
                                    BottomBarScreen(
                                        route = "compartir",
                                        title = "Compartir",
                                        icon = Icons.Default.Share
                                    ),
                                )
                                BottomNavigationBar(navController = navController, screens)
                            }
                        },
                        content = {
                            NavHost(navController, startDestination = "login") {
                                composable(route = "splash") {
                                    SplashScreen {
                                        navController.navigate("login")
                                    }
                                }
                                composable(route = "home") {
                                    Log.d("HOME", "home screen")
                                    HomeScreen(navController, homeScrennViewModel)
                                }
                                composable(route = "pokemon") {
                                    Log.d("POKEMON", "pokemons screen")
                                    PokemonScreen(navController)
                                }
                                composable(route = "reset_password") {
                                    Log.d("ROUTER", "reset password")
                                    ResetPasswordScreen(navController)
                                }
                                composable(route = "profile") {
                                    Log.d("ROUTER", "profile")
                                    ProfileScreen(navController, profileScrennViewModel)
                                }
                                composable(route = "pokemon/edit?pokemon_id={pokemon_id}",
                                    arguments = listOf(
                                        navArgument("pokemon_id") {
                                            type = NavType.IntType
                                            defaultValue = 0
                                        }
                                    ),
                                    content = { entry ->
                                        val pokemonId = entry.arguments?.getInt("pokemon_id")!!
                                        pokemonDetailScrennViewModel.pokemonId = pokemonId
                                        PokemonDetailScreen(
                                            navController,
                                            pokemonDetailScrennViewModel
                                        )
                                    })
                                composable(route = "login") {
                                    Log.d("ROUTER", "login")
                                    LoginScreen(loginScrennViewModel, navController)
                                }
                                composable(route = "compartir",
                                    content = {
                                        if (showDialog) {
                                            AlertDialog(
                                                onDismissRequest = {
                                                    showDialog = false
                                                },
                                                title = {
                                                    Text(text = "\u200E \u200E \u200E\u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E  Gracias por compartir",)
                                                        },
                                                text = {
                                                    val imageUrl1 =
                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/WhatsApp.svg/800px-WhatsApp.svg.png"
                                                    val imageUrl2 =
                                                        "https://logowik.com/content/uploads/images/633_facebook_icon.jpg"
                                                    val uri = Uri.parse(imageUrl1)
                                                    val uri2 = Uri.parse(imageUrl2)
                                                    val painter1= rememberImagePainter(
                                                        data = uri.scheme + "://" + uri.host + uri.path + (if (uri.query != null) uri.query else ""),
                                                        builder = {
                                                            // You can apply transformations here if needed
                                                            transformations(CircleCropTransformation())
                                                        }
                                                    )
                                                    val painter2 = rememberImagePainter(
                                                        data = uri2.scheme + "://" + uri2.host + uri2.path + (if (uri2.query != null) uri2.query else ""),
                                                        builder = {
                                                            // You can apply transformations here if needed
                                                            transformations(CircleCropTransformation())
                                                        }
                                                    )
                                                    Column {
                                                        Row{
                                                            Text("\u200E \u200E \u200E  WhatsApp",
                                                                modifier=Modifier.padding(20.dp))
                                                            Text("\u200E \u200E \u200E  Facebook",
                                                                modifier=Modifier.padding(20.dp))
                                                        }
                                                        Row{
                                                                Image(
                                                                    painter = painter1,
                                                                    contentDescription = null, // Set a proper content description if required
                                                                    modifier = Modifier.size(
                                                                        130.dp,
                                                                        130.dp)
                                                                        .clickable {
                                                                            shareOnWhatsAppOrFacebook("https://www.google.com")
                                                                        }
                                                                    )


                                                                Image(
                                                                    painter = painter2,
                                                                    contentDescription = null, // Set a proper content description if required
                                                                    modifier = Modifier.size(
                                                                        130.dp,
                                                                        130.dp
                                                                    )
                                                                )
                                                        }

                                                    }
                                                },
                                                confirmButton = {
                                                    TextButton(
                                                        onClick = {
                                                            // Lógica para manejar el botón de confirmación
                                                            showDialog = false
                                                        }
                                                    ) {
                                                        Text("Regresar")
                                                    }
                                                },
                                            )
                                        }
                                    }
                                )
                            }})}}}}}
