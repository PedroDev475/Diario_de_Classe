package com.senai.diario_de_classe.data.worker

import androidx.work.CoroutineWorker
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.senai.diario_de_classe.data.repository.UsuarioRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class UsuarioSyncWorker @AssistedInject constructor(

    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: UsuarioRepository
    ) : CoroutineWorker(context, workerParams) {

        override suspend fun doWork(): Result {
            android.util.Log.i("UsuarioSyncWorker", "Executando sincronização...")
            return try {
                repository.sincronizarUsuarios()

                android.util.Log.i("UsuarioSyncWorker", "Sincronização concluída com sucesso.")
                Result.success()
            } catch (e: Exception) {
                android.util.Log.e("UsuarioSyncWorker", "Erro na sincronização", e)
                Result.retry()
            }

        }

    }
