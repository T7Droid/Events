package co.thyagoneves.eventnews.views

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import co.thyagoneves.eventnews.databinding.FragmentEventDetailBinding
import co.thyagoneves.eventnews.model.EventsListItem
import co.thyagoneves.eventnews.repositories.EventsRepository
import co.thyagoneves.eventnews.rest.RetrofitService
import co.thyagoneves.eventnews.viewmodels.EventsViewModel
import co.thyagoneves.eventnews.viewmodels.EventsViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val EVENT = "event"

class EventDetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var event: EventsListItem? = null
    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventsViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            event = it.getSerializable(EVENT) as EventsListItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            EventsViewModelFactory(EventsRepository(retrofitService))
        )[EventsViewModel::class.java]
        viewModel.checkInSucessful.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Checkin realizado com sucesso!", Toast.LENGTH_SHORT)
                .show()
        }

        binding.tvDetailTitle.text = event?.title
        if (event?.people?.size!! > 0) {
            binding.tvPeople.text = "Quem estará presente: ${event?.people.toString()}"
        } else {
            binding.tvPeople.text = "Quem estará presente: Ninguém por enquanto."
        }

        binding.etNome.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isNotBlank() && p0.toString()
                        .isNotEmpty() && binding.etEmail.text.isNotEmpty() && binding.etEmail.text.isNotBlank()
                ) {
                    binding.btnCheckin.setBackgroundColor(Color.parseColor("#228B22"))
                    binding.btnCheckin.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.btnCheckin.isEnabled = true
                } else {
                    binding.btnCheckin.setBackgroundColor(Color.parseColor("#d3d3d3"))
                    binding.btnCheckin.setTextColor(Color.parseColor("#000000"))
                    binding.btnCheckin.isEnabled = false
                }
            }
        })

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isNotBlank() && p0.toString()
                        .isNotEmpty() && binding.etNome.text.isNotEmpty() && binding.etNome.text.isNotBlank()
                ) {
                    binding.btnCheckin.setBackgroundColor(Color.parseColor("#228B22"))
                    binding.btnCheckin.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.btnCheckin.isEnabled = true
                } else {
                    binding.btnCheckin.setBackgroundColor(Color.parseColor("#d3d3d3"))
                    binding.btnCheckin.setTextColor(Color.parseColor("#000000"))
                    binding.btnCheckin.isEnabled = false
                }
            }
        })

        binding.btnCheckin.setOnClickListener {
            viewModel.doCheckIn(
                event!!.id,
                binding.etNome.text.toString(),
                binding.etEmail.text.toString()

            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllEvents()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}