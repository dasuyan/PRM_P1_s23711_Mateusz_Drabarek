package pl.edu.pja.prm_p1_s23711_mateusz_drabarek.data

import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.R
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.model.Task

object DataSource {
    val tasks = mutableListOf(
        Task(
            "Take out the trash",
            "I have to put plastic & paper recyclables in front of the house, a trash truck will collect it on Monday",
            R.drawable.trash
        ),
        Task(
            "Start working on the PRM project",
            "Remember to carefully watch the tutorials. I might try to implement the Material 3 design",
            R.drawable.programming
        ),
        Task(
            "Feed my cats",
            "Leo needs to first take his vitamin pill on an empty stomach. Don't give them the salmon flavor!",
            R.drawable.cat
        ),
        Task(
            "Do the dishes",
            "Watch out for mom's favourite green-painted plate. If I break it, I'm dead...",
            R.drawable.dishes
        )
    )
}