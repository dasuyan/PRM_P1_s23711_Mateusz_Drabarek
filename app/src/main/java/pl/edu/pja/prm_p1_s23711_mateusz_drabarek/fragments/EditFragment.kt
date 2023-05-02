package pl.edu.pja.prm_p1_s23711_mateusz_drabarek.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.Navigable
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.R
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.adapters.TaskImagesAdapter
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.data.DataSource
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.databinding.FragmentEditBinding
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.model.Task


class EditFragment(private val task: Task?) : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private lateinit var adapter: TaskImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEditBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TaskImagesAdapter()
        binding.images.apply {
            adapter = this@EditFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        if (task == null) {
            handleNewTask()
        } else {
            handleEditTask()
        }

    }

    private fun handleNewTask() {
        binding.editFragmentTitle.setText(R.string.edit_fragment_adding)

        binding.btSave.setOnClickListener {
            val newTask = Task(
                name = binding.taskName.text.toString(),
                description = binding.description.text.toString(),
                resId = adapter.selectedIdRes
            )
            DataSource.tasks.add(newTask)

            (activity as? Navigable)?.navigate(Navigable.Destination.List, null)
        }
    }

    private fun handleEditTask() {
        binding.editFragmentTitle.setText(R.string.edit_fragment_editing)

        val position = task?.let { adapter.images.indexOf(it.resId) }
        adapter.selectedPosition = position!!
        adapter.notifyItemChanged(adapter.selectedPosition)
        binding.taskName.setText(task?.name)
        binding.description.setText(task?.description)

        binding.btSave.setOnClickListener {
            val taskIndex = DataSource.tasks.indexOf(task)
            DataSource.tasks[taskIndex].name = binding.taskName.text.toString()
            DataSource.tasks[taskIndex].description = binding.description.text.toString()
            DataSource.tasks[taskIndex].resId = adapter.selectedIdRes

            (activity as? Navigable)?.navigate(Navigable.Destination.List, null)
        }
    }
}