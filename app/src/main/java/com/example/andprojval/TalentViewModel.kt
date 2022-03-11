package com.example.andprojval


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TalentViewModel: ViewModel() {
    var talents = mutableStateOf( listOf<String>())
    fun getTalentsFeral(){
        Firebase.firestore
            .collection("talents")
            .get()
            .addOnSuccessListener {
                val tlnt = mutableListOf<String>()
                it.documents.forEach{ doc ->
                    tlnt.add( doc.get("talntferal").toString())
                }
                talents.value = tlnt
            }
    }
    fun getTalentsBalance(){
        Firebase.firestore
            .collection("talents")
            .get()
            .addOnSuccessListener {
                val tlnt = mutableListOf<String>()
                it.documents.forEach{ doc ->
                    tlnt.add( doc.get("talntbalance").toString())
                }
                talents.value = tlnt
            }
    }
    fun getTalentsHavoc(){
        Firebase.firestore
            .collection("talents")
            .get()
            .addOnSuccessListener {
                val tlnt = mutableListOf<String>()
                it.documents.forEach{ doc ->
                    tlnt.add( doc.get("talnthavoc").toString())
                }
                talents.value = tlnt
            }
    }
    fun getTalentsFire(){
        Firebase.firestore
            .collection("talents")
            .get()
            .addOnSuccessListener {
                val tlnt = mutableListOf<String>()
                it.documents.forEach{ doc ->
                    tlnt.add( doc.get("talntfire").toString())
                }
                talents.value = tlnt
            }
    }
    fun getTalentsBlood(){
        Firebase.firestore
            .collection("talents")
            .get()
            .addOnSuccessListener {
                val tlnt = mutableListOf<String>()
                it.documents.forEach{ doc ->
                    tlnt.add( doc.get("talntblood").toString())
                }
                talents.value = tlnt
            }
    }

}