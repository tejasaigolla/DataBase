package com.example.kr.KOTLIN

import android.content.ContentValues
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_barang.*

class studentActivity : AppCompatActivity() {

    var id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang)

        try {
            var bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id !=0){
                txNami.setText(bundle.getString("MainActNami"))
                txJenkis.setText(bundle.getString("MainActJenkis"))
                txHungry.setText(bundle.getString("MainActHungry"))
            }
        }catch (ex: Exception){
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)

        val itemDelete: MenuItem = menu.findItem(R.id.action_delete)

        if (id ==0){
            itemDelete.isVisible = false
        }else{
            itemDelete.isVisible = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_save -> {
                var dbAdapter = DBAdapter(this)

                var values = ContentValues()
                values.put("sname", srname.text.toString())
                values.put("collegename", tgJenkis.text.toString())
                values.put("rollnumber", tgHungry.text.toString())

                if (id == 0){
                    val mID = dbAdapter.insert(values)

                    if (mID > 0){
                        Toast.makeText(this, "Saved Succesfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Failed to save the  data", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    var selectionArgs = arrayOf(id.toString())
                    val mID = dbAdapter.update(values, "Id=?", selectionArgs)
                    if (mID > 0){
                        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this, "Failed to update the data", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            R.id.action_delete ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Delete The  Data")
                builder.setMessage("This Data Will No Longer be Available")

                builder.setPositiveButton("Delete") {dialog: DialogInterface?, which: Int ->
                    var dbAdapter = DBAdapter(this)
                    val selectionArgs = arrayOf(id.toString())
                    dbAdapter.delete("Id=?", selectionArgs)
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                    finish()
                }
                builder.setNegativeButton("Cancel"){dialog: DialogInterface?, which: Int ->  }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
