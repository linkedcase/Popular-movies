package ru.abelyh.movies.model.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.abelyh.movies.data.User

class FirebaseRepositoryImpl : FirebaseRepository {

    // создали объект для записи в БД
    private var database: DatabaseReference = Firebase.database.reference

    override fun updateUserData(firebaseUser: User, uid: String) {
        // создали нашего пользователя в Firebase Realtime
        database.child("users").child(uid).setValue(firebaseUser)

    }


}