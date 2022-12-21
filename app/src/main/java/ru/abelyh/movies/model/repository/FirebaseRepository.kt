package ru.abelyh.movies.model.repository

import ru.abelyh.movies.data.User

interface FirebaseRepository {
    fun updateUserData (firebaseUser: User, uid: String)
}