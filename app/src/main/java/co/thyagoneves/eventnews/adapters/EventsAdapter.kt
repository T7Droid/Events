package co.thyagoneves.eventnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.thyagoneves.eventnews.databinding.ItemViewBindingBinding
import co.thyagoneves.eventnews.model.EventsListItem
import co.thyagoneves.eventnews.utils.formatCurrency
import com.bumptech.glide.Glide
import java.math.BigDecimal
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class EventsAdapter(private val onItemClicked: (EventsListItem) -> Unit) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    private var events = mutableListOf<EventsListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBindingBinding.inflate(inflater, parent, false)
        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event, onItemClicked)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun setData(newList: List<EventsListItem>) {
        this.events = newList.toMutableList()
        notifyDataSetChanged()
    }

    class EventsViewHolder(val binding: ItemViewBindingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(eventItem: EventsListItem, onItemClicked: (EventsListItem) -> Unit) {
            binding.tvTitle.text = eventItem.title
            val formatter = SimpleDateFormat("dd/MM/yyyy");
            val dateString = "Data: ${formatter.format(Date(eventItem.date))}"
            binding.tvDate.text = dateString
            Glide.with(itemView).load(eventItem.image).into(binding.ivImage)
            binding.tvDescription.text = eventItem.description
            val price = "Preço: ${formatCurrency(eventItem.price)}"
            binding.tvPrice.text = price

            itemView.setOnClickListener {
                onItemClicked(eventItem)
            }
        }
    }
}