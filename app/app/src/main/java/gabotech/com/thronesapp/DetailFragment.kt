package gabotech.com.thronesapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_character.*
import kotlinx.android.synthetic.main.header_character.*
import kotlinx.android.synthetic.main.item_character.view.*

class DetailFragment : Fragment() {

    companion object{
        fun newInstance(id: String): DetailFragment {
            val instance = DetailFragment()
            val args = Bundle()

            args.putString("key_id", id)
            instance.arguments = args
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("key_id")
        val character = id?.let { CharactersRepo.findCharacterByID(it) }

        character?.let {
            with(character){
                lblName.text = name
                lblTitle.text = title
                lblActor.text = actor
                lblBorn.text = born
                lblParents.text =  "$father & $mother"
                lblQuote.text = quote
                lblSpouse.text = spouse

                val houseIcon = House.getIcon(house.name)
                val overlayColor = House.getOverlayColor(house.name)
                val baseColor = House.getBaseColor(house.name)

                context?.let {
                    imgOverlay.background = ContextCompat.getDrawable(it, overlayColor)
                    btnHouse.backgroundTintList = ContextCompat.getColorStateList(it, baseColor)
                    btnHouse.setImageDrawable(ContextCompat.getDrawable(it, houseIcon))
                }
                Picasso.get()
                    .load(img)
                    .placeholder(R.drawable.got_background)
                    .into(imgCharacter)
            }
        }

        // Lambda form
        btnHouse.setOnClickListener {
            if (character != null)
                showDialog(character.house)
            //Toast.makeText(context, character?.house?.words , Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialog(house: House){
        val dialog = HouseDialog.newInstance(house)
        dialog.show(childFragmentManager, "house_dialog")
    }

}