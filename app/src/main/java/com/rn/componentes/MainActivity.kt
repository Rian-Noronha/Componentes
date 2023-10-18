package com.rn.componentes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import com.rn.componentes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSpinner()
        initSeekBar()
        initSwitch()
        binding.chkEnabled.isChecked = true
        binding.skbValue.progress = 20
        binding.spnNames.setSelection(2)
        binding.rgOptions.check(R.id.rbOption2)
        binding.btnShowValues.setOnClickListener{showValues()}
    }

    private fun initSpinner(){
        val names = arrayOf("Rian", "Ana", "Ramiro", "Raniel", "Bryan", "Lavyne", "Lariane")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, names)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnNames.adapter = adapter

    }

    private fun initSeekBar(){
        binding.skbValue.setOnSeekBarChangeListener(
            object:SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                   binding.txtValue.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            }
        )
    }

    private fun initSwitch(){
        binding.swtEnabled.setOnCheckedChangeListener{compoundButton, b ->

            binding.chkEnabled.isEnabled = compoundButton.isChecked
            binding.tgbEnabled.isEnabled = b
        }
    }

    private fun showValues(){
        val idSelectRadio = binding.rgOptions.checkedRadioButtonId
        val radio = findViewById<RadioButton>(idSelectRadio)
        val enabledText = getString(
            if(binding.chkEnabled.isChecked) R.string.text_enabled else R.string.text_disabled
        )

        val message = getString(R.string.msg_result, enabledText, binding.skbValue.progress, binding.spnNames.selectedItem, radio.text.toString())

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}