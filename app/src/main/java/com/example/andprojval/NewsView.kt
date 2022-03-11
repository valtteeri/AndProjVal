package com.example.andprojval


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewsView: ViewModel() {
    var news = mutableStateOf( listOf<String>() )

    init {
        Firebase.firestore
            .collection("news")
            .addSnapshotListener {value,error ->
                if(error != null){
                    //error handle
            } else if(value !=null && !value.isEmpty) {
                    val nws = mutableListOf<String>()
                    for(d in value.documents){
                        nws.add(d.get("nws").toString() )
                        nws.add(d.get("txt").toString() )
                    }
                    news.value = nws
            }
        }
    }
}