package com.example.foe_sklad.mainFragmentVetva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.foe_sklad.R
import com.example.foe_sklad.sklad.Sklad
import com.example.foe_sklad.databinding.FragmentPozriSkladBinding


/**
 *Tento [Fragment] slúži na kontrolu správnosti stavu skladu
 */
class PozriSkladFragment : Fragment() {

    /**
     * Zobrazí stav skladu vo forme textu a rozdelí ho na pätice podľa doby
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sklad = Sklad(requireContext())
        val binding = DataBindingUtil.inflate<FragmentPozriSkladBinding>(inflater,
            R.layout.fragment_pozri_sklad,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.skladText.setText(sklad.getSklad().joinToString("\n") { element ->
            if ((sklad.getSklad().indexOf(element) + 1) % 5 == 0) "$element\n"
            else element.toString() })
        return binding.root
    }
}