package pl.edu.pja.prm_p1_s23711_mateusz_drabarek

import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.model.Task

interface Navigable {
    enum class Destination {
        List, Add, Preview
    }

    fun navigate(to: Destination, task: Task?)
}