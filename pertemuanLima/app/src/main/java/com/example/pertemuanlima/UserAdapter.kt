package com.example.pertemuanlima

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.pertemuanlima.model.DataItem

class UserAdapter(private val users: MutableList<DataItem>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent,false)
        return ListViewHolder(
            view
        )
    }

    fun addUser(newUsers: DataItem) {
        users.add(newUsers)
        notifyItemInserted(users.lastIndex)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        users.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = users[position]
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(80,80).placeholder(R.drawable.icon_avatar))
            .transform(CircleCrop())
            .into(holder.ivAvatar)
        holder.tvUsername.text = "Nama  : ${user.firstName} ${user.lastName}"
        holder.tvEmail.text = "Email  : ${user.email}"
        holder.tvAlamat.text = "Alamat : ${user.alamat}"
        holder.tvTotalIuranBulanan.text = "Iuran Bulanan : ${user.totalIuranBulanan.toString()}"
        holder.tvTotalIuranIndividu.text = "Iuran Individu : ${user.totalIuranIndividu.toString()}"
        holder.tvTotalIuranAkhir.text = "Iuran Akhir : ${user.totalIuranAkhir.toString()}"
        holder.tvPengeluaranIuran.text = "Pengeluaran Iuran : ${user.pengeluaranIuran.toString()}"
        holder.tvPemanfaatanIuaran.text = "Pemanfaatan Iuran : ${user.pemanfaatanIuran.toString()}"

    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvUsername: TextView = itemView.findViewById(R.id.itemName)
        var tvEmail: TextView = itemView.findViewById(R.id.itemEmail)
        var tvAlamat: TextView = itemView.findViewById(R.id.itemAlamat)
        var tvTotalIuranBulanan: TextView = itemView.findViewById(R.id.itemTotalIuranBulanan)
        var tvTotalIuranIndividu: TextView = itemView.findViewById(R.id.itemTotalIuranIndividu)
        var tvTotalIuranAkhir: TextView = itemView.findViewById(R.id.itemTotalIuranAkhir)
        var tvPengeluaranIuran: TextView = itemView.findViewById(R.id.itemPengeluaranIuran)
        var tvPemanfaatanIuaran: TextView = itemView.findViewById(R.id.itemPemanfaatanIuran)
        var ivAvatar: ImageView = itemView.findViewById(R.id.itemAvatar)

    }
}