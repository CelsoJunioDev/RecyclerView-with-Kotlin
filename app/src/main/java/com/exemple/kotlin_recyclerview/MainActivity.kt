package com.exemple.kotlin_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {
    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

    }
    fun inserirItem(view: View){

        val index = Random.nextInt(8)
        val novoItem = ExampleItem(
            R.drawable.ic_android,
            "Novo item na posição $index",
            "Linha 02"
        )
        exampleList.add(index, novoItem)
        //adapter.notifyDataSetChanged()
        adapter.notifyItemInserted(index)
    }
    fun removerItem(view: View){

        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }
    override fun onItemClick(position: Int) {
        Toast.makeText(this, "item $position", Toast.LENGTH_SHORT).show()
        val itemClicado = exampleList[position]
        itemClicado.text1 = "Clicado"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem>{
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size){
            val drawable = when (i % 3){
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sun
            }
            val item = ExampleItem(drawable, "Item $i", "Linha 02")
            list += item
        }
        return list
    }


}