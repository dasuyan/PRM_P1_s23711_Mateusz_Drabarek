package pl.edu.pja.prm_p1_s23711_mateusz_drabarek.model

import androidx.annotation.DrawableRes

data class Task(
    var name: String,
    var description: String,
    @DrawableRes
    var resId: Int
)
