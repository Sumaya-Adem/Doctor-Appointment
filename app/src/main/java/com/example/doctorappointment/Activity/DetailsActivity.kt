package com.example.doctorappointment.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.doctorappointment.Model.DoctorsModel
import com.example.doctorappointment.R
import com.example.doctorappointment.databinding.ActivityDetailsBinding

class DetailsActivity :BaseActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var item : DoctorsModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backIcon.setOnClickListener {
            onBackPressed()
        }
        getBundle()
    }


    private fun getBundle() {
       item =intent.getParcelableExtra("object")!!

        binding.apply {
            doctorName.text = item.Name
            specialistName.text = item.Special
            dateTxt.text = item.Date
            timeTxt.text = item.Time
            patientTxt.text = item.Patiens
            bioTxt.text = item.Biography
            address.text = item.Address

            experinceYears.text =item.Expriense.toString()+"years"
            ratingTxt.text = "${item.Rating}"

            makeBtn.setOnClickListener {
                finish()
            }
            websitebtn.setOnClickListener {
                val i=Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(item.Site))
                startActivity(i)
            }
            messagebtn.setOnClickListener {
                val uri = Uri.parse("smsto:${item.Mobile}")
                val intent=Intent(Intent.ACTION_SENDTO,uri)
                intent.putExtra("sms_body","the SMS text ")
                startActivity(intent)
            }
            callbtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${item.Mobile}")
                startActivity(intent)
            }
            direcbtn.setOnClickListener {
                val intent=Intent(Intent.ACTION_VIEW,Uri.parse(item.Location))
                startActivity(intent)
            }
            sharebtn.setOnClickListener {
              val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, item.Name)
                intent.putExtra(Intent.EXTRA_TEXT,item.Name+""+item.Address+
                ""+item.Mobile)
                startActivity(Intent.createChooser(intent,"share via "))
            }
            Glide.with(this@DetailsActivity)
                .load(item.Picture)
                    .into(img)
        }
    }
}