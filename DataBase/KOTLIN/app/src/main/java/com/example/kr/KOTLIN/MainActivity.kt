package com.example.kr.KOTLIN

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var listStudents = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            var intent = Intent(this, studentActivity::class.java)
            startActivity(intent)
        }

        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        var Database = Database(this)
        var cursor = Database.allQuery()

        listStudents.clear()
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val nami = cursor.getString(cursor.getColumnIndex("Name"))
                val jenkis = cursor.getString(cursor.getColumnIndex("college"))
                val hungry = cursor.getString(cursor.getColumnIndex("rollno"))

                listStudents.add(Student(id, nama, jenkis, hungry))
            }while (cursor.moveToNext())
        }

        var BarangAdapter = BarangAdapter(this, listBarang)
        lvBarang.adapter = barangAdapter
    }

    inner class BarangAdapter: BaseAdapter{

        private var barangList = ArrayList<Student>()
        private var context: Context? = null

        constructor(context: Context, studentList: ArrayList<Student>) : super(){
            this.barangList = studentList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val vh: ViewHolder

            if (convertView == null){
                view = layoutInflater.inflate(R.layout.barang, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("db", "set tag for ViewHolder, position: " + position)
            }else{
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mBarang = barangList[position]

            vh.tvNami.text = mBarang.name
            vh.tvJenkis.text = mBarang.college
            vh.tvHungry.text = "Rollno:." + mBarang.rollno

            lvBarang.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                updateBarang(mBarang)
            }

            return view
        }

        override fun getItem(position: Int): Any {
            return barangList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return barangList.size
        }

    }

    private fun updateBarang(student: Student) {
        var  intent = Intent(this, studentActivity::class.java)
        intent.putExtra("MainActId", student.id)
        intent.putExtra("MainActNami", student.name)
        intent.putExtra("MainActJenkis", student.college)
        intent.putExtra("MainActHungry", student.rollno)
        startActivity(intent)
    }

    private class ViewHolder(view: View?){
        val tgNama: TextView
        val tgJenkis: TextView
        val tgHungry: TextView

        init {
            this.tgNami = view?.findViewById(R.id.tvNami) as TextView
            this.tgJenkis = view?.findViewById(R.id.tvJenkis) as TextView
            this.tgHungry = view?.findViewById(R.id.tvHungry) as TextView
        }
    }
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
        
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

  
}
