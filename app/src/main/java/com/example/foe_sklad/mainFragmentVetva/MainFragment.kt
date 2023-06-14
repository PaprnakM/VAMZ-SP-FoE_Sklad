package com.example.foe_sklad.mainFragmentVetva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.example.foe_sklad.R
import com.example.foe_sklad.sklad.Sklad
import com.example.foe_sklad.databinding.FragmentMainBinding

/**
 * Hlavný [Fragment]. Tu sa užívateľ objaví po zapnutí aplikácie.
 * Vyprázdňuje back-stack a počíta maximálny počet možných odomknutí CE.
 */
class MainFragment : Fragment() {

    /**
     * Priraďuje onClickListener k Buttonom, nastavuje LifeCycle, maže back-stack
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
            R.layout.fragment_main,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.OdomkniCEbutton.setOnClickListener{
                view : View -> view.findNavController().navigate(R.id.action_mainFragment_to_odomkniExpediciuFragment)
        }
        binding.PridajTovarButton.setOnClickListener{
                view : View -> view.findNavController().navigate(R.id.action_mainFragment_to_pridajTovarFragment)
        }
        binding.PozriSkladButton.setOnClickListener{
                view : View -> view.findNavController().navigate(R.id.action_mainFragment_to_pozriSkladFragment)}

        val maxExpedicii = maxExpedicii()
        binding.PocetCEnum.text = maxExpedicii.toString()

        val fragmentManager = parentFragmentManager
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        return binding.root
    }

    /**
     * Počíta a vracia minimum z podielov všetkých tovarov
     * a im príslušných požiadaviek
     * @return maxExpedicii
     */
    fun maxExpedicii() : Int {
        val sklad = Sklad(requireContext())
        var maxExpedicii = Int.MAX_VALUE
        var indexCyklu = 0
        var indexPoziadavky = 0
        for (i in sklad.getSklad().indices) {
            if (sklad.getPoziadavky()[indexPoziadavky] != 0) {
                if (sklad.getSklad()[i] / sklad.getPoziadavky()[indexPoziadavky] <= maxExpedicii) {
                    maxExpedicii = sklad.getSklad()[i] / sklad.getPoziadavky()[indexPoziadavky]
                }
            }
            indexCyklu++
            if (indexCyklu % 5 == 0) indexPoziadavky++
        }
        return maxExpedicii
    }
}