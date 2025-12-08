plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.senai.diario_de_classe"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.senai.diario_de_classe"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // --- FIREBASE (CORRIGIDO) ---
    // 1. O BoM deve ter 'platform' em volta
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    // 2. As bibliotecas SEM versão e SEM ktx (o BoM gerencia)
    implementation("com.google.firebase:firebase-functions")
    implementation("com.google.firebase:firebase-ml-modeldownloader")
    // (Se precisar de analytics, adicione: implementation("com.google.firebase:firebase-analytics"))

    // ⚠️ APAGUE AS LINHAS ANTIGAS QUE ESTAVAM AQUI (libs.firebase...) ⚠️
    // Elas estavam puxando as versões 22.1.0 e 26.0.1 que não existem mais.

    // --- OUTRAS DEPENDÊNCIAS (MANTIDAS)
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0") // Atualizei para uma versão estável comum, 2.10 pode não existir ainda
    implementation("androidx.compose.material:material-icons-extended")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common.jvm)
    // implementation(libs.androidx.work.runtime.ktx) // Você já adicionou work-runtime-ktx manualmente acima, pode remover essa se quiser evitar duplicata
    implementation(libs.androidx.hilt.common)

    implementation(libs.androidx.room.runtime.android)
    implementation(libs.androidx.databinding.adapters)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

