package com.example.hasdeneme

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class form_menu : AppCompatActivity() {

    private lateinit var txtSecilenTarih: TextView
    private lateinit var spinnerFloors: Spinner
    private lateinit var spinnerRooms: Spinner
    private lateinit var btnKaydet: Button
    private lateinit var btnTarihSec: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_menu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtSecilenTarih = findViewById(R.id.txtSecilenTarih)
        spinnerFloors = findViewById(R.id.spinnerFloors)
        spinnerRooms = findViewById(R.id.spinnerRooms)
        btnKaydet = findViewById(R.id.btnKaydet)
        btnTarihSec = findViewById(R.id.btnTarihSec)

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayoutChecklist)

        val gorevListesi = listOf(
            "Yatak Linenleri ",
            "Havlular ",
            "Çöp kovaları ",
            "Tuvalet Zemin ",
            "Tuvalet Lavabo ",
            "Klozet ",
            "Klozet kenarları ve kapakları",
            "Tuvalet sifon düğmeleri ",
            "Koltuklar, perdelerin ele temas eden kenarları",
            "Banyo Lavabo ",
            "Duş Alanı ",
            "Toz Alımı ",
            "Aynalar ",
            "Halı Süpürme/ Ahşap Zemin ",
            "Sandalyeler/Koltuklar ",
            "Çalışma Masası/Valizlik ",
            "Dolap içi askılar ",
            "Dolap içleri ve kulplar ",
            "Telefon ",
            "Kumanda ",
            "Televizyon ",
            "Su ısıtıcı ",
            "Kapı kolu/Balkon Trabzanları ",
            "Pencere Kolu ",
            "Yatak Başı lamba düğmeleri ve Prizleri ",
            "Prizler ve Lamba Düğmeleri ",
            "Klima kontrol paneli ",
            "Mini Bar ",
            "Kasa "
        )

        for (gorev in gorevListesi) {
            val row = LinearLayout(this)
            row.orientation = LinearLayout.HORIZONTAL
            row.setPadding(10, 10, 10, 10)

            val tv = TextView(this)
            tv.text = gorev
            tv.textSize = 18f
            tv.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)

            val cb = CheckBox(this)
            row.addView(tv)
            row.addView(cb)

            linearLayout.addView(row)
        }

        // Kat Spinner
        val katlar = listOf("WEST 16", "WEST 15", "WEST 14PS")
        val katAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, katlar)
        spinnerFloors.adapter = katAdapter

        // Oda Spinner
        val odalarHaritasi = mapOf(
            "WEST 16" to (1675..1695).map { "Oda $it" },
            "WEST 15" to (1575..1595).map { "Oda $it" },
            "WEST 14PS" to (1475..1495).map { "Oda $it" }
        )

        spinnerFloors.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long
            ) {
                val secilenKat = katlar[position]
                val odalar = odalarHaritasi[secilenKat] ?: emptyList()
                val odaAdapter = ArrayAdapter(this@form_menu, android.R.layout.simple_spinner_dropdown_item, odalar)
                spinnerRooms.adapter = odaAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Tarih Seçimi
        btnTarihSec.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, y, m, d ->
                txtSecilenTarih.text = "Tarih: $d/${m + 1}/$y"
            }, year, month, day)
            dpd.show()
        }

        // Kaydet Butonu
        btnKaydet.setOnClickListener {
            val oda = spinnerRooms.selectedItem?.toString() ?: "Oda seçilmedi"
            Toast.makeText(this, "$oda başarıyla kaydedildi!", Toast.LENGTH_SHORT).show()
           }
       }
}