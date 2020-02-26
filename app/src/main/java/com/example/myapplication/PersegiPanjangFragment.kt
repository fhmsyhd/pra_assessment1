package com.example.myapplication


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentPersegiPanjangBinding
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*

/**
 * A simple [Fragment] subclass.
 */
class PersegiPanjangFragment : Fragment() {
    private lateinit var binding: FragmentPersegiPanjangBinding
    private var luas = 0
    private var keliling = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(
//            inflater, R.layout.fragment_persegi_panjang, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_persegi_panjang,container,false)



        binding.btnHitung1.setOnClickListener{ view: View ->
            val panjang = tv_panjang.text.toString().toInt()
            val lebar = tv_lebar.text.toString().toInt()

            if (!TextUtils.isEmpty(tv_panjang.text.toString()) && !TextUtils.isEmpty(tv_lebar.text.toString())){
                luas = panjang * lebar
                keliling = 2 *(panjang+lebar)

                binding.tvLuas1.text = luas.toString()
                binding.tvKel1.text = keliling.toString()
                binding.tvLuas1.visibility = View.VISIBLE
                binding.tvKel1.visibility = View.VISIBLE
            } else {
                Toast.makeText(context,"HARAP DI LENGKAPI!",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnShare1.setOnClickListener {
            val intent = ShareCompat.IntentBuilder.from(context as Activity)
                .setType("plain/text")
                .setText("Luas = $luas \n" +
                        " Keliling = $keliling")
                .intent
                .setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_SUBJECT, "Hasil")
            startActivity(intent)
        }
        return binding.root
    }



}
