package com.gl4.tp2mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    val recycler : RecyclerView by lazy { findViewById(R.id.recycler) }
    val filterEditText : EditText by lazy { findViewById(R.id.filterEditText) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var matieres = listOf<String>("Cours","TP")

        var studentsCours1 = arrayListOf<Student>(
            Student("Achour", "Ines", "F"),
            Student("Unknown", "Unknown", "M"),
            Student("Test", "Test2", "M")
        )

        var studentsCours2 = arrayListOf<Student>(
            Student("Foulen", "Foulen", "M"),
            Student("Achour", "Achour", "F"),
            Student("Foulena", "Ben Foulen", "F"),
        )

        var students = studentsCours1

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, matieres)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                /*var toast = Toast.makeText(applicationContext, matieres[position], Toast.LENGTH_SHORT)
                toast.show()*/
                if(position ==0){
                    recycler.adapter = StudentsAdapter(studentsCours1)
                }
                else{
                    recycler.adapter = StudentsAdapter(studentsCours2)
                }
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        var studentsAdapter = StudentsAdapter(students)

        recycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = studentsAdapter
        }

        filterEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                studentsAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


    }

}