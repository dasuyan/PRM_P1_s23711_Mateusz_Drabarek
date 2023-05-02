package pl.edu.pja.prm_p1_s23711_mateusz_drabarek

import androidx.recyclerview.widget.DiffUtil
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.model.Task

class TaskCallback(private val notSorted: List<Task>, private val sorted: List<Task>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = notSorted.size

    override fun getNewListSize(): Int = sorted.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        notSorted[oldItemPosition] === sorted[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        notSorted[oldItemPosition] == sorted[newItemPosition]
}