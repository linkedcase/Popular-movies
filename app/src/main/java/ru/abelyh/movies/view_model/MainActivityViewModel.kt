package ru.abelyh.movies.view_model

import com.google.firebase.auth.FirebaseUser
import ru.abelyh.movies.data.User
import ru.abelyh.movies.model.repository.FirebaseRepository
import ru.abelyh.movies.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {

    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateUserData(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}