package com.example.myapplication


import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.FragmentPersegiPanjangBinding
import com.example.myapplication.databinding.FragmentSegitigaBinding
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import kotlinx.android.synthetic.main.fragment_segitiga.*

/**
 * A simple [Fragment] subclass.
 */
class SegitigaFragment : Fragment() {
    private var luas = 0
    private var keliling = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSegitigaBinding>(
            inflater, R.layout.fragment_segitiga, container, false)



        binding.btnHitung2.setOnClickListener{ view: View ->
            val alas = tv_alas.text.toString().toInt()
            val tinggi = tv_tinggi.text.toString().toInt()

            var sisiMiring = 0
            if (!TextUtils.isEmpty(tv_alas.text.toString()) && !TextUtils.isEmpty(tv_tinggi.text.toString())){
                luas = (alas * tinggi)/2
                sisiMiring = Math.sqrt(((alas*alas)+(tinggi*tinggi)).toDouble()).toInt()
                keliling = alas + tinggi + sisiMiring

                binding.tvLuas2.text = luas.toString()
                binding.tvKel2.text = keliling.toString()
                binding.tvLuas2.visibility = View.VISIBLE
                binding.tvKel2.visibility = View.VISIBLE
            } else {
                Toast.makeText(context,"HARAP DI LENGKAPI!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnShare2.setOnClickListener {
            val intent = ShareCompat.IntentBuilder.from(context as Activity)
                .setType("plain/text")
                .setText("Luas = $luas \n" +
                        " Keliling = $keliling")
                .intent
            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(context,"Sharing Not Available",
                    Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }


}
