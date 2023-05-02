package pl.edu.pja.prm_p1_s23711_mateusz_drabarek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.R
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.databinding.TaskImageBinding

class TaskImageViewHolder(private val binding: TaskImageBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(resId: Int, isSelected: Boolean) {
        binding.image.setImageResource(resId)
        binding.selectedFrame.visibility =
            if (isSelected) View.VISIBLE else View.INVISIBLE
    }
}

class TaskImagesAdapter : RecyclerView.Adapter<TaskImageViewHolder>() {

    val images = listOf(
        R.drawable.cat,
        R.drawable.dishes,
        R.drawable.trash,
        R.drawable.programming
    )
    var selectedPosition: Int = 0

    val selectedIdRes: Int
        get() = images[selectedPosition]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskImageViewHolder {
        val binding = TaskImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskImageViewHolder(binding).also { vh ->
            binding.root.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = vh.layoutPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: TaskImageViewHolder, position: Int) {
        holder.bind(images[position], position == selectedPosition)
    }

    override fun getItemCount(): Int = images.size
}