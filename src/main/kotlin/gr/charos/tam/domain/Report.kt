package gr.charos.tam.domain

import java.time.LocalDate

data class Report(  val name: String,
                    val remainingDays: Int,
                    val provisionalEnd: LocalDate) {

}
