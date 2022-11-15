package com.example.vistaprincipal.recyclerview

import com.google.firebase.firestore.Exclude
import java.util.*

class Post(val nombre: String? = null, val tipo: String? = null, val pais: String? = null) {
    @Exclude
    @set:Exclude
    @get:Exclude
    var uid: String? = null

    constructor(): this (null, null, null)
}