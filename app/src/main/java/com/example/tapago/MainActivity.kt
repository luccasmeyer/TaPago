package com.example.tapago

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels // Adicionado para instanciar o ViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel // Adicionado
import androidx.lifecycle.ViewModelProvider // Adicionado
import com.example.tapago.presentation.DatabaseViewModel // Import do seu ViewModel de teste

class MainActivity : AppCompatActivity() {

    private val testViewModel: DatabaseViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val database = AppDatabase.getDatabase(applicationContext)
                @Suppress("UNCHECKED_CAST")
                return DatabaseViewModel(database.ExerciseDao()) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        testViewModel.forcarCriacaoDoBanco()
    }
}