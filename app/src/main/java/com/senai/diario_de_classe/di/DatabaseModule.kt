package com.senai.diario_de_classe.di
import android.content.Context
import androidx.room.Room
import com.senai.diario_de_classe.data.local.dao.UsuarioDao
import com.senai.diario_de_classe.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

}