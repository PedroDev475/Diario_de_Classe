package com.senai.diario_de_classe.data.local.db
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.senai.diario_de_classe.data.local.dao.UsuarioDao
import com.senai.diario_de_classe.data.local.entity.UsuarioEntity

    @Database(entities = [UsuarioEntity::class], version = 5)
    @TypeConverters(Converters::class)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun usuarioDao(): UsuarioDao
    }
