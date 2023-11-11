package ru.deltadelete.lab10.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.google.android.material.ripple.RippleUtils
import com.squareup.picasso.Picasso
import ru.deltadelete.lab10.R
import ru.deltadelete.lab10.database.entities.Country
import ru.deltadelete.lab10.databinding.CountryItemBinding
import ru.deltadelete.lab10.utils.ItemCallbacks

public class CountryAdapter(context: Context, private val items: MutableList<Country>) :
    ArrayAdapter<Country>(context, R.layout.country_item, items) {
    @LayoutRes
    private val layout: Int = R.layout.country_item
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    var itemCallbacks: ItemCallbacks<Country> = ItemCallbacks<Country>()

    @Override
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: CountryItemBinding = if (convertView == null) {
            DataBindingUtil.inflate(inflater, layout, parent, false)
        } else {
            DataBindingUtil.bind<CountryItemBinding>(convertView)!!
        }

        val item: Country = getItem(position)
        binding.item = item
        binding.itemCallbacks = itemCallbacks

        return binding.root
    }

    override fun getItem(position: Int): Country {
        return items[position]
    }

    override fun add(item: Country?) {
        item?.let {
            super.add(it)
        }
    }

    override fun remove(item: Country?) {
        item?.let {
            items.remove(it)
            super.remove(it)
            notifyDataSetChanged()
        }
    }
}