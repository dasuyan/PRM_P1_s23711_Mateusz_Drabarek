package pl.edu.pja.prm_p1_s23711_mateusz_drabarek.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.Navigable
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.TaskCallback
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.data.DataSource
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.databinding.ListItemBinding
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.fragments.ListFragment
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.model.Task


class TaskViewHolder(private val binding: ListItemBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.name.text = task.name
        binding.description.text = task.description
        binding.image.setImageResource(task.resId)
    }
}

class TasksAdapter(private val listFragment: ListFragment, private val navigable: Navigable) : RecyclerView.Adapter<TaskViewHolder>() {
    private val handler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())
    private val data = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TaskViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView.setOnClickListener{
            navigable.navigate(Navigable.Destination.Preview, data[position])
        }

        holder.itemView.setOnLongClickListener{
            val context = it.context
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Remove task")
            builder.setMessage("Do you want to remove this task?")
            builder.setPositiveButton("Yes") { dialog, _ ->
                DataSource.tasks.remove(DataSource.tasks[position])
                listFragment.loadData()
                notifyDataSetChanged()

                dialog.dismiss()
                val text = "Task successfully removed"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
            true
        }
    }

    override fun getItemCount(): Int = data.size

    fun replace(newData: List<Task>) {
        val callback = TaskCallback(data, newData)
        data.clear()
        data.addAll(newData)
        val result = DiffUtil.calculateDiff(callback)
        handler.post{
            result.dispatchUpdatesTo(this)
        }
    }
}