package co.thyagoneves.eventnews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.thyagoneves.eventnews.repositories.EventsRepository
import java.lang.IllegalArgumentException

class EventsViewModelFactory constructor(private val repository: EventsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            EventsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}