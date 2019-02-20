package bernardo.bernardinhio.timersforaddingremovingitemsfromrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import java.util.*


class AdapterRV internal constructor(private val arrayListProducts: ArrayList<ProductModel>) : RecyclerView.Adapter<AdapterRV.ViewHolderRV>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRV {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewInflated = layoutInflater.inflate(R.layout.fragment_product_item, parent, false)
        return ViewHolderRV(viewInflated)
    }

    override fun onBindViewHolder(holder: ViewHolderRV, position: Int) {

        val (imageName, title, location, price) = arrayListProducts[position]


        // set image resource
        val context = holder.ivProductImage.context
        val id = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        holder.ivProductImage.setImageResource(id)
        // set other views values
        holder.tvProductTitle.text = title
        holder.tvProductLocation.text = "In $location"
        holder.tvProductPrice.text = "$price€"
    }

    override fun getItemCount(): Int {
        return arrayListProducts.size
    }

    inner class ViewHolderRV(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProductImage: ImageView = itemView.findViewById<View>(R.id.iv_product_image) as ImageView
        val tvProductTitle: TextView = itemView.findViewById<View>(R.id.tv_product_title) as TextView
        val tvProductLocation: TextView = itemView.findViewById<View>(R.id.tv_product_location) as TextView
        val tvProductPrice: TextView = itemView.findViewById<View>(R.id.tv_product_price) as TextView
    }
}