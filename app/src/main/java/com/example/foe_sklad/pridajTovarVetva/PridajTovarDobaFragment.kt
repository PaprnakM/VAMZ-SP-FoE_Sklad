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
import com.example.foe_sklad.databinding.FragmentPridajTovarDobaBinding

/**
 * Tento [Fragment] spracuváva bundle z predošlého fragmentu
 * a mení podľa neho vzhľad tlačidiel.
 * Podľa výberu buď pridáva tovar do skladu alebo posúva užívateľa na nasledujúci fragment
 */
class PridajTovarDobaFragment : Fragment(), View.OnClickListener {
    private lateinit var m_sklad: Sklad
    private lateinit var binding: FragmentPridajTovarDobaBinding

    /**
     * Mení text tlačidiel podľa prevziateho bundle.
     * Priraďuje týmto tlačidlám onClickListener
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_pridaj_tovar_doba,container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.tovar1button.text = arguments?.getString("tovar1")
        binding.tovar2button.text = arguments?.getString("tovar2")
        binding.tovar3button.text = arguments?.getString("tovar3")
        binding.tovar4button.text = arguments?.getString("tovar4")
        binding.tovar5button.text = arguments?.getString("tovar5")

        binding.okDoba.setOnClickListener(this)
        binding.tovar1button.setOnClickListener(this)
        binding.tovar2button.setOnClickListener(this)
        binding.tovar3button.setOnClickListener(this)
        binding.tovar4button.setOnClickListener(this)
        binding.tovar5button.setOnClickListener(this)

        m_sklad = Sklad(requireContext())
        return binding.root
    }

    /**
     * Podľa voľby užívateľa buď pridáva tovar do skladu a vracia ho na hlavný fragment,
     * alebo vytvára nový bundle a spolu s užívateľom ho posiela na nasledujúci fragment.
     * @exception NumberFormatException
     */
    override fun onClick(view : View) {
        if (view.id == R.id.okDoba) {
            val index = arguments?.getInt("indexDoba")!!
            var pocetTovar = 0
            for (i in 0 until 5) {
                    if (binding.NumInputDoba.text.toString().isNotEmpty()) {
                        try {
                            pocetTovar = binding.NumInputDoba.text.toString().toInt()
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }
                    }
                    m_sklad.setSklad(index * 5 + i,
                        m_sklad.getSklad()[index*5+i] + pocetTovar)
                    }
            if (pocetTovar != 0) m_sklad.saveToFile()
            view.findNavController().navigate(R.id.action_pridajTovarDobaFragment_to_mainFragment)
        } else {
            val bundle = Bundle()
            val indexDoba = arguments?.getInt("indexDoba")!!
            bundle.putInt("indexDoba", indexDoba)

            when (view.id) {
                R.id.tovar1button -> {
                    bundle.putInt("indexTovar", 0)
                }
                R.id.tovar2button -> {
                    bundle.putInt("indexTovar", 1)
                }
                R.id.tovar3button -> {
                    bundle.putInt("indexTovar", 2)
                    }
                R.id.tovar4button -> {
                    bundle.putInt("indexTovar", 3)
                }
                R.id.tovar5button -> {
                    bundle.putInt("indexTovar", 4)
                }
            }
            view.findNavController()
                .navigate(R.id.action_pridajTovarDobaFragment_to_pridajTovarTovarFragment, bundle)
        }
    }
}
