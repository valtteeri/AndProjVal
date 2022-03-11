package com.example.andprojval

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

const val HOME_ROUTE = "home"
const val TALENT_ROUTE = "talent"

@Composable
fun MainView() {
    val userVM = viewModel<UserViewModel>()

    if(userVM.username.value.isEmpty()){
        LoginView(userVM)
    }else{
        MainScaffoldView()
    }
}

@Composable
fun MainScaffoldView() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {TopBar()},
        bottomBar = { BottomBarView(navController)},
        content = { MainContentView(navController)}
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME_ROUTE ){
        composable( route = HOME_ROUTE){ HomeView()}
        composable( route = TALENT_ROUTE){ TalentView()}
    }
}

@Composable
fun HomeView() {
    val nwsVM: NewsView = viewModel()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        NewsPageTitle()
        Spacer(modifier = Modifier.height(10.dp))
        nwsVM.news.value.forEach{
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = it)
        }
    }
}

@Composable
fun NewsPageTitle() {
    Text(text = "World of Warcraft RWF leaders",textAlign = TextAlign.Center, fontSize = 25.sp,
        modifier = Modifier.width(800.dp))
}

@Composable
fun TalentView() {
    val talentVM: TalentViewModel = viewModel()

    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        OutlinedButton(onClick = { talentVM.getTalentsFeral() }) {
            Text(text = "Feral")
        }
        OutlinedButton(onClick = { talentVM.getTalentsBalance() }) {
            Text(text = "Balance")
        }
        OutlinedButton(onClick = { talentVM.getTalentsFire() }) {
            Text(text = "Fire")
        }
        OutlinedButton(onClick = { talentVM.getTalentsHavoc() }) {
            Text(text = "Havoc")
        }
        OutlinedButton(onClick = { talentVM.getTalentsBlood() }) {
            Text(text = "Blood")
        }
    }
    Column(modifier = Modifier
        .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Raid Talents")
        Spacer(modifier = Modifier.height(20.dp))
        talentVM.talents.value.forEach{
            Divider(thickness = 2.dp)
            Text(text = it)
            Divider(thickness = 2.dp)
        }
    }
}

@Composable
fun BottomBarView(navController: NavHostController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(color = 0xFF7661A5)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = "home",
        modifier = Modifier.clickable { navController.navigate(HOME_ROUTE) })
        Icon(painter = painterResource(id = R.drawable.ic_talents), contentDescription = "talents",
            modifier = Modifier.clickable { navController.navigate(TALENT_ROUTE) })
    }
}


@Composable
fun TopBar() {
    var userVM = viewModel<UserViewModel>()
    
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(color = 0xFF7661A5))
        .padding(10.dp),
    horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = userVM.username.value, color = Color.White)
        OutlinedButton(onClick = { userVM.logoutUser() }) {
            Text(text = "Log out")
        }
    }
}


@Composable
fun LoginView(userVM: UserViewModel) {
    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(value = email, onValueChange = { email = it}, label = { Text(text="Email")})
        OutlinedTextField(value = password, onValueChange = { password = it},label = { Text(text="Password")},
        visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { userVM.loginUser(email,password) }) {
            Text(text = "Login")
            
        }
    }
}
