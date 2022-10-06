package co.thyagoneves.eventnews.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import co.thyagoneves.eventnews.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://5f5a8f24d44d640016169133.mockapi.io/api/events eventos lista

        //check in do evento: POST no o: https://5f5a8f24d44d640016169133.mockapi.io/api/checkin
        //O POST deve conter os dados do interessado (Nome, e-mail) e o id do evento. Ex:
        //
        //{ "eventId": "1", "name": "Otávio", "email": "otavio_souza@..." }

    }
}