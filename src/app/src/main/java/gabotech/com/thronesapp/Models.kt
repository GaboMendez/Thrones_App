package gabotech.com.thronesapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.util.*

data class Character(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var born: String,
    var title: String,
    var actor: String,
    var quote: String,
    var father: String,
    var mother: String,
    var spouse: String,
    var img: String,
    var house: House
)

data class House(
    var name: String,
    var region: String,
    var words: String,
    var img: String
) : Serializable {
    companion object {
        private val DEFAULT_PALETTE = arrayOf(R.color.starkOverlay)
        private val colors = mapOf(
            Pair("stark", arrayOf(R.color.starkOverlay, R.color.starkBase, R.drawable.ic_stark)),
            Pair("lannister", arrayOf(R.color.lannisterOVerlay, R.color.lannisterBase, R.drawable.ic_lannister)),
            Pair("tyrell", arrayOf(R.color.tyrellOVerlay, R.color.tyrellBase, R.drawable.ic_tyrell)),
            Pair("arryn", arrayOf(R.color.arrynOVerlay, R.color.arrynBase, R.drawable.ic_arryn)),
            Pair("targaryen", arrayOf(R.color.targaryenOVerlay, R.color.targaryenBase, R.drawable.ic_targaryen)),
            Pair("baratheon", arrayOf(R.color.baratheonOVerlay, R.color.baratheonBase, R.drawable.ic_baratheon)),
            Pair("greyjoy", arrayOf(R.color.greyjoyOVerlay, R.color.greyjoyBase, R.drawable.ic_greyjoy)),
            Pair("frey", arrayOf(R.color.freyOVerlay, R.color.freyBase, R.drawable.ic_frey)),
            Pair("tully", arrayOf(R.color.tullyOVerlay, R.color.tullyBase, R.drawable.ic_tully))
        )

        fun getOverlayColor(houseId: String): Int {
            val palette = getPalette(houseId)
            return palette[0]
        }

        fun getBaseColor(houseId: String): Int {
            val palette = getPalette(houseId)
            return palette[1]
        }

        fun getIcon(houseId: String): Int {
            val palette = getPalette(houseId)
            return palette[2]
        }

        private fun getPalette(houseId: String) : Array<Int> {
            val palette = colors[houseId]
            return palette ?: DEFAULT_PALETTE
            // El operador elvis "?:" significa:
            // Si el valor del lado izquierdo es null, devuleve el valor del lado derecho
        }
    }
}
