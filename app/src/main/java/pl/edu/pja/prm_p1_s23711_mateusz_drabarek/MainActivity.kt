package pl.edu.pja.prm_p1_s23711_mateusz_drabarek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.fragments.EditFragment
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.fragments.ListFragment
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.fragments.PreviewFragment
import pl.edu.pja.prm_p1_s23711_mateusz_drabarek.model.Task

class MainActivity : AppCompatActivity(), Navigable {
    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, listFragment, listFragment.javaClass.name)
            .commit()
    }

    override fun navigate(to: Navigable.Destination, task: Task?) {
        supportFragmentManager.beginTransaction().apply {
            when (to) {
                Navigable.Destination.List -> {
                    replace(R.id.container, listFragment, listFragment.javaClass.name)
                    addToBackStack(ListFragment::class.java.name)
                }
                Navigable.Destination.Add -> {
                    replace(R.id.container, EditFragment(task), EditFragment::class.java.name)
                    addToBackStack(EditFragment::class.java.name)
                }
                Navigable.Destination.Preview -> {
                    replace(R.id.container, PreviewFragment(task), PreviewFragment::class.java.name)
                    addToBackStack(PreviewFragment::class.java.name)
                }
            }
        }.commit()
    }
}