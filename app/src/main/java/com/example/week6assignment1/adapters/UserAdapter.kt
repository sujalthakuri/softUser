package com.example.week6assignment1.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.week6assignment1.R
import com.example.week6assignment1.Storage
import com.example.week6assignment1.models.User
import com.bumptech.glide.Glide


class UserAdapter(
    val lstUsers: ArrayList<User>,
    val context: Context
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    val storage = Storage()

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivUser: ImageView
        val tvName: TextView
        val tvAge: TextView
        val tvAddress: TextView
        val tvGender: TextView
        val ivDelete: ImageView

        init {
            ivUser = view.findViewById(R.id.ivUser)
            tvName = view.findViewById(R.id.tvName)
            tvAge = view.findViewById(R.id.tvAge)
            tvAddress = view.findViewById(R.id.tvAddress)
            tvGender = view.findViewById(R.id.tvGender)
            ivDelete = view.findViewById(R.id.ivDelete)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_adapter, parent, false)
        return UserAdapter.UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = lstUsers[position]

        holder.tvName.text = user.name
        holder.tvAddress.text = user.address
        holder.tvAge.text = user.age.toString()

        when (user.gender) {
            'M' -> {
                holder.tvGender.text = "Male"
                holder.ivUser.setImageResource(R.drawable.male)
            }
            'F' -> {
                holder.tvGender.text = "Female"
                holder.ivUser.setImageResource(R.drawable.female)
            }
            else -> {
                holder.tvGender.text = "Others"
                holder.ivUser.setImageResource(R.drawable.others)
            }
        }
        if(!TextUtils.isEmpty(user.image_url)) {
            Glide.with(context).load(user.image_url).into(holder.ivUser)
        }
        holder.ivDelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete?")

            builder.setMessage("Do you want to delete this user?")

            builder.setIcon(android.R.drawable.ic_dialog_info)

            builder.setPositiveButton("YES"){ _, _ ->
                storage.deleteUser(user)
                Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show()

                lstUsers.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, lstUsers.size)
                holder.itemView.visibility = View.GONE
            }
            builder.setNegativeButton("No"){ _, _ ->

            }

            val alert: AlertDialog = builder.create()
            alert.setCancelable(true)
            alert.show()
        }

    }

    override fun getItemCount(): Int {
        return lstUsers.size
    }
}