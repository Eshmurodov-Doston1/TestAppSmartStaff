package com.example.testappsmartstaff

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testappsmartstaff.databinding.ActivityMainBinding
import com.example.testappsmartstaff.databinding.DialogAppBinding
import com.example.testappsmartstaff.models.Result
import com.example.testappsmartstaff.viewmodel.AppViewModel
import com.example.testappsmartstaff.viewmodel.ResultViewModel
import com.example.testappsmartstaff.viewmodel.ViewModelFactory
import java.lang.Math.abs


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var appViewModel:AppViewModel
    lateinit var resultViewModel:ResultViewModel

    var position = 0
    lateinit var paint: Paint
    var layoutWidth:Int = 0
    var layoutHeight:Int = 0

    var widthMy:MutableLiveData<Int> =MutableLiveData()
    var heightMy:MutableLiveData<Int> = MutableLiveData()
    private var maxWidth = 0
    private var maxHeight = 0
    private var minSize = 150

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE
            window.statusBarColor = Color.parseColor("#1A222C")
            window.navigationBarColor =Color.parseColor("#1A222C")



            clear?.setOnClickListener {
                myCanva2.clearDraw()
                myCanva1.clearDraw()
            }

            appViewModel = ViewModelProvider(this@MainActivity,ViewModelFactory(position,layoutWidth,layoutHeight))[AppViewModel::class.java]

            appViewModel.progress.observe(this@MainActivity, Observer {
              if (it!=0){
                  seekBar.max = it
              }
            })
            seekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    if (p2){
                        position = p1
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }

            })




            size.setOnClickListener {

                minSize = myCanva1.height / 4
                maxWidth = myCanva1.width * 2
                maxHeight = myCanva1.height * 2



                var alertDialog = AlertDialog.Builder(this@MainActivity,R.style.BottomSheetDialogThem)
                val create = alertDialog.create()
                var dialogAppBinding = DialogAppBinding.inflate(LayoutInflater.from(this@MainActivity),null,false)
                create.setView(dialogAppBinding.root)
                dialogAppBinding.apply {
                    resultViewModel = ViewModelProvider(this@MainActivity,ViewModelFactory(position,layoutWidth,layoutHeight))[ResultViewModel::class.java]
                    width.setText("${myCanva1.b?.width}")
                    height.setText("${myCanva1.b?.height}")

                    ok.setOnClickListener {
                       var widthApp = width.text.toString().trim()
                       var heightApp = height.text.toString().trim()
                        if (widthApp.isNotEmpty() && heightApp.isNotEmpty()){
                            if (widthApp.toInt() <= minSize || heightApp.toInt() <= minSize) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Size must be higher than $minSize",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (widthApp.toInt() > maxWidth || heightApp.toInt() > maxHeight) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "The size exceeded the set limit",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                myCanva1.changeSize(widthApp.toInt(), heightApp.toInt())
                                myCanva2.changeSize(widthApp.toInt(), heightApp.toInt())
                                create.dismiss()
                            }
                        }
                    }
                    cancle.setOnClickListener {
                        create.dismiss()
                    }
                }
                create.show()
            }

            generate.setOnClickListener {
                myCanva1?.startDraw(algorithm1.selectedItemPosition, abs(seekBar.progress - seekBar.max))
                myCanva2?.startDraw(algorithm2.selectedItemPosition, abs(seekBar.progress - seekBar.max))
            }



        }
    }

}