package presenter

import Entities.Items
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_user.view.*
import net.justaddmusic.tetristest.R

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(items: Items?) {
        itemView.UserName.text = items?.name
        itemView.siteAdminIcon.visibility = if (items!!.hasWiki) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view)
        }
    }

}