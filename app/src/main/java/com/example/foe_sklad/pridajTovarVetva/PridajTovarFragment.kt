package com.example.foe_sklad.pridajTovarVetva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.foe_sklad.R
import com.example.foe_sklad.databinding.FragmentPridajTovarBinding

/**
 *Tento [Fragment] obsahuje [Button] pre každú dobu.
 * Posiela ďalej názov jednotlivých tovarov a index doby pomocou [Bundle]
 */
class PridajTovarFragment : Fragment(), View.OnClickListener {

    /**
     * Vytvára, vypĺňa a posiela ďalej bundle s potrebnými informáciami
     * o dobe, ktorej príslušný button užívateľ použil.
     * Presúva užívateľa na nasledujúci fragment
     */
    override fun onClick(view: View) {
        val bundle = Bundle()

        when (view.id) {
            R.id.IAbutton -> {
                bundle.putString("tovar1", "látka")
                bundle.putString("tovar2", "eben")
                bundle.putString("tovar3", "šperky")
                bundle.putString("tovar4", "železo")
                bundle.putString("tovar5", "vápenec")
                bundle.putInt("indexDoba", 0)
            }

            R.id.EMAbutton -> {
                bundle.putString("tovar1", "meď")
                bundle.putString("tovar2", "zlato")
                bundle.putString("tovar3", "žula")
                bundle.putString("tovar4", "med")
                bundle.putString("tovar5", "alabaster")
                bundle.putInt("indexDoba", 1)
            }

            R.id.HMAbutton -> {
                bundle.putString("tovar1", "tehly")
                bundle.putString("tovar2", "sklo")
                bundle.putString("tovar3", "sušené bylinky")
                bundle.putString("tovar4", "lano")
                bundle.putString("tovar5", "soľ")
                bundle.putInt("indexDoba", 2)
            }

            R.id.LMAbutton -> {
                bundle.putString("tovar1", "čadič")
                bundle.putString("tovar2", "mosadz")
                bundle.putString("tovar3", "strelný prach")
                bundle.putString("tovar4", "hodváb")
                bundle.putString("tovar5", "prášok z mastenca")
                bundle.putInt("indexDoba", 3)
            }

            R.id.CAbutton -> {
                bundle.putString("tovar1", "káva")
                bundle.putString("tovar2", "papier")
                bundle.putString("tovar3", "porcelán")
                bundle.putString("tovar4", "decht")
                bundle.putString("tovar5", "drôt")
                bundle.putInt("indexDoba", 4)
            }

            R.id.InAbutton -> {
                bundle.putString("tovar1", "koks")
                bundle.putString("tovar2", "hnojivo")
                bundle.putString("tovar3", "guma")
                bundle.putString("tovar4", "textil")
                bundle.putString("tovar5", "veľrybí olej")
                bundle.putInt("indexDoba", 5)
            }

            R.id.PEbutton -> {
                bundle.putString("tovar1", "azbest")
                bundle.putString("tovar2", "výbušniny")
                bundle.putString("tovar3", "strojové súčiastky")
                bundle.putString("tovar4", "benzín")
                bundle.putString("tovar5", "cínové plechy")
                bundle.putInt("indexDoba", 6)
            }

            R.id.MEbutton -> {
                bundle.putString("tovar1", "polotovary")
                bundle.putString("tovar2", "železobetón")
                bundle.putString("tovar3", "dochucovadlá")
                bundle.putString("tovar4", "luxusné materiály")
                bundle.putString("tovar5", "obalové materiály")
                bundle.putInt("indexDoba", 7)
            }

            R.id.PMEbutton -> {
                bundle.putString("tovar1", "genetické informácie")
                bundle.putString("tovar2", "priemyslené filtre")
                bundle.putString("tovar3", "obnoviteľné zdroje")
                bundle.putString("tovar4", "polovodiče")
                bundle.putString("tovar5", "oceľ")
                bundle.putInt("indexDoba", 8)
            }

            R.id.CEbutton -> {
                bundle.putString("tovar1", "bionické údaje")
                bundle.putString("tovar2", "elektromagnety")
                bundle.putString("tovar3", "plyn")
                bundle.putString("tovar4", "plasty")
                bundle.putString("tovar5", "roboty")
                bundle.putInt("indexDoba", 9)
            }

            R.id.TEbutton -> {
                bundle.putString("tovar1", "nutričný výskum")
                bundle.putString("tovar2", "papierobetón")
                bundle.putString("tovar3", "konzervanty")
                bundle.putString("tovar4", "inteligentné materiály")
                bundle.putString("tovar5", "sklobetón")
                bundle.putInt("indexDoba", 10)
            }

            R.id.FEbutton -> {
                bundle.putString("tovar1", "riasy")
                bundle.putString("tovar2", "biogeochemické údaje")
                bundle.putString("tovar3", "nanočastice")
                bundle.putString("tovar4", "čistená voda")
                bundle.putString("tovar5", "supravodiče")
                bundle.putInt("indexDoba", 11)
            }

            R.id.AFbutton -> {
                bundle.putString("tovar1", "dáta pre UI")
                bundle.putString("tovar2", "bioplasty")
                bundle.putString("tovar3", "nanodrôt")
                bundle.putString("tovar4", "ohybné batérie")
                bundle.putString("tovar5", "transestorový plyn")
                bundle.putInt("indexDoba", 12)
            }

            R.id.OFbutton -> {
                bundle.putString("tovar1", "umelé šupiny")
                bundle.putString("tovar2", "biosvetlá")
                bundle.putString("tovar3", "koraly")
                bundle.putString("tovar4", "perly")
                bundle.putString("tovar5", "planktón")
                bundle.putInt("indexDoba", 13)
            }

            R.id.VFbutton -> {
                bundle.putString("tovar1", "kryptomena")
                bundle.putString("tovar2", "dátové kryštály")
                bundle.putString("tovar3", "zlatá ryža")
                bundle.putString("tovar4", "nanity")
                bundle.putString("tovar5", "čajovníkovy hodváb")
                bundle.putInt("indexDoba", 14)
            }
        }
        view.findNavController().navigate(R.id.action_pridajTovarFragment_to_pridajTovarDobaFragment, bundle)
    }

    /**
     * Priraďuje onClickListener každému tlačidlu
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPridajTovarBinding>(inflater,
            R.layout.fragment_pridaj_tovar,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.IAbutton.setOnClickListener(this)
        binding.EMAbutton.setOnClickListener(this)
        binding.HMAbutton.setOnClickListener(this)
        binding.LMAbutton.setOnClickListener(this)
        binding.CAbutton.setOnClickListener(this)
        binding.InAbutton.setOnClickListener(this)
        binding.PEbutton.setOnClickListener(this)
        binding.MEbutton.setOnClickListener(this)
        binding.PMEbutton.setOnClickListener(this)
        binding.CEbutton.setOnClickListener(this)
        binding.TEbutton.setOnClickListener(this)
        binding.FEbutton.setOnClickListener(this)
        binding.AFbutton.setOnClickListener(this)
        binding.OFbutton.setOnClickListener(this)
        binding.VFbutton.setOnClickListener(this)
        return binding.root
    }
}