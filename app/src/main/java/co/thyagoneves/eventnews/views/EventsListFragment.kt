package co.thyagoneves.eventnews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.thyagoneves.eventnews.R
import co.thyagoneves.eventnews.adapters.EventsAdapter
import co.thyagoneves.eventnews.databinding.FragmentEventsListBinding
import co.thyagoneves.eventnews.model.EventsListItem
import co.thyagoneves.eventnews.rest.RetrofitService
import co.thyagoneves.eventnews.viewmodels.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class EventsListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentEventsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventsViewModel by viewModels()
    @Inject lateinit var retrofitService: RetrofitService
    private lateinit var eventsAdapter: EventsAdapter

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
    ): View {
        _binding = FragmentEventsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsAdapter = EventsAdapter (requireActivity()){ eventItem ->
            openDetailScreen(eventItem)
        }

        binding.rvEvents.apply {
            hasFixedSize()
            adapter = eventsAdapter
        }

        viewModel.eventsList.observe(viewLifecycleOwner){
            eventsAdapter.setData(it)
        }
    }

    private fun openDetailScreen(event: EventsListItem){
        findNavController().navigate(R.id.eventDetailFragment, bundleOf("event" to event))
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