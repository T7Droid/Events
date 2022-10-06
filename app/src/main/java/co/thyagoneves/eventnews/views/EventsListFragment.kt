package co.thyagoneves.eventnews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.thyagoneves.eventnews.R
import co.thyagoneves.eventnews.databinding.FragmentEventsListBinding
import co.thyagoneves.eventnews.repositories.EventsRepository
import co.thyagoneves.eventnews.rest.RetrofitService
import co.thyagoneves.eventnews.viewmodels.EventsViewModel
import co.thyagoneves.eventnews.viewmodels.EventsViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EventsListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentEventsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventsViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, EventsViewModelFactory(EventsRepository(retrofitService))).get(EventsViewModel::class.java)

        binding.btnNav.setOnClickListener {
            findNavController().navigate(R.id.eventDetailFragment, bundleOf("ouro" to "ouro"))
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}