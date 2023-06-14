package com.example.foe_sklad.pridajTovarVetva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.foe_sklad.R
import com.example.foe_sklad.sklad.Sklad
import com.example.foe_sklad.databinding.FragmentPridajTovarTovarBinding

/**
 * Tento [Fragment] pridáva užívateľom zadané množstvo konkrétneho tovaru do skladu
 * a následne ho vracia na hlavný fragment
 */
class PridajTovarTovarFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentPridajTovarTovarBinding

    /**
     * Klasický inflate fragmentu
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_pridaj_tovar_tovar,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.okTovar.setOnClickListener(this)
        return binding.root
    }

    /**
     * Spracuváva prevziaty bundle a pridáva zadané množstvo tovaru do skladu.
     * Vracia užívateľa na hlavný fragment
     * @exception NumberFormatException
     */
    override fun onClick(view : View) {
        val sklad = Sklad(requireContext())
        val indexDoba = arguments?.getInt("indexDoba")!!
        val indexTovar = arguments?.getInt("indexTovar")!!
        if (binding.NumInputTovar.text.toString().isNotEmpty()) {
            try {
                sklad.setSklad(indexDoba * 5 + indexTovar,
                    binding.NumInputTovar.text.toString().toInt()
                            + sklad.getSklad()[indexDoba * 5 + indexTovar])
                sklad.saveToFile()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }
        view.findNavController()
            .navigate(R.id.action_pridajTovarTovarFragment_to_mainFragment)
    }
}