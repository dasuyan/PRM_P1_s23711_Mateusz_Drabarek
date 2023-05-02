package pl.edu.pja.prm_p1_s23711_mateusz_drabarek.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.Navigable
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.adapters.TasksAdapter
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.data.DataSource
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var adapter: TasksAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigable = activity as? Navigable
        adapter = navigable?.let { TasksAdapter(this, it) }
        loadData()

        binding.list.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btAdd.setOnClickListener {
            (activity as? Navigable)?.navigate(Navigable.Destination.Add, null)
        }
    }

    fun loadData() {
        binding.allTasksValue.text = DataSource.tasks.size.toString()
        DataSource.tasks.sortBy { it.name }

        adapter?.replace(DataSource.tasks)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
}