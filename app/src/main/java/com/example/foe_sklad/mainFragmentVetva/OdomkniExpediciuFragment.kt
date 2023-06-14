package com.example.foe_sklad.mainFragmentVetva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.foe_sklad.R
import com.example.foe_sklad.sklad.Sklad
import com.example.foe_sklad.databinding.FragmentOdomkniExpediciuBinding

/**
 * Tento [Fragment] má na starosti požiadavky na odomknutie CE.
 * Pýta si ich od skladu na zobrazenie a v prípade aktualizácie
 * posiela nové požiadavky naspäť na uloženie.
 */
class OdomkniExpediciuFragment : Fragment(), View.OnClickListener {
    private var m_editTexts: List<EditText> =listOf()
    private lateinit var m_sklad: Sklad

    /**
     * Vytvára list všetkých EditTextov a priraďuje im správny text
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentOdomkniExpediciuBinding>(inflater,
            R.layout.fragment_odomkni_expediciu,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.OdomkniCEokButton.setOnClickListener(this)

        val editTexts: List<EditText> = listOf (binding.NumInputIA, binding.NumInputEMA, binding.NumInputHMA, binding.NumInputLMA,
            binding.NumInputCA, binding.NumInputIna, binding.NumInputPE, binding.NumInputME, binding.NumInputPME,
            binding.NumInputCE, binding.NumInputTE, binding.NumInputFE, binding.NumInputAF, binding.NumInputOF, binding.NumInputVF)

        val sklad = Sklad(requireContext())
        for (i in editTexts.indices) {
            editTexts[i].setText(sklad.getPoziadavky()[i].toString())
        }
        m_editTexts = editTexts
        m_sklad = sklad
        return binding.root
    }

    /**
     * Aktualizuje požiadavky a odrátava podľa nich tovary zo skladu.
     * Vracia užívateľa na hlavný fragment.
     * @exception NumberFormatException
     */
    override fun onClick(view : View) {

        val newPoziadavky = IntArray(m_editTexts.size)
        for (i in m_editTexts.indices) {
            if (m_editTexts[i].text.toString().isEmpty()) {
                newPoziadavky[i] = 0
            } else {
                try {
                    newPoziadavky[i] = m_editTexts[i].text.toString().toInt()
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
            }
        }
        m_sklad.setPoziadavky(newPoziadavky)
        for (i in newPoziadavky.indices) {
            for (j in 0 until 5) {
                m_sklad.setSklad(i*5+j, m_sklad.getSklad()[i*5+j] - m_sklad.getPoziadavky()[i])
            }
        }
        m_sklad.saveToFile()
        view.findNavController().navigate(R.id.action_odomkniExpediciuFragment_to_mainFragment)
    }
}