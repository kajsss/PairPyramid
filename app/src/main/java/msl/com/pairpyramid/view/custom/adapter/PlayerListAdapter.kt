package msl.com.pairpyramid.view.custom.adapter

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader.TileMode
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_player_list.view.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.model.Player
import msl.com.pairpyramid.view.entry.MakeEntryActivity
import org.jetbrains.anko.sdk25.coroutines.onClick


class PlayerListAdapter constructor() : RecyclerView.Adapter<PlayerListAdapter.ViewHolder>() {

    var item: List<Player>? = null
    var removedPosition: Int = -1
    var context: Context? = null
    lateinit var deleteListener: MakeEntryActivity.DeleteItemListener

    constructor(context: Context) : this() {
        this.context = context
    }

    constructor(context: Context, listener: MakeEntryActivity.DeleteItemListener) : this() {
        this.context = context
        this.deleteListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent!!.getContext())
        val view = inflater.inflate(R.layout.item_player_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(item!!.get(position), position == removedPosition, this@PlayerListAdapter, deleteListener)
    }

    override fun getItemCount(): Int = item!!.size

    fun itemSwiped(position: Int) {
        removedPosition = position
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        item = item?.minusElement(item!![position])
        notifyItemRemoved(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(player: Player, removedLayout: Boolean, adapter: PlayerListAdapter, listener: MakeEntryActivity.DeleteItemListener) = with(itemView) {
            if (removedLayout) {
                user_list_layout.visibility = View.GONE
                user_list_delete_layout.visibility = View.VISIBLE

                user_delete.onClick {
                    var alertDialogBuilder = AlertDialog.Builder(itemView.context)
                    alertDialogBuilder.setTitle("Player Delete")
                    run {
                        alertDialogBuilder
                                .setMessage("Are you sure want to delete this Player?")
                                .setCancelable(true) // True allows you to use the back button to exit the dialog, false does not
                                .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, which ->
                                    dialog.dismiss()
                                }).setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

                            //Database Delete
                            listener.doAction(player.id)
                            adapter.removeItem(adapterPosition)
                        })
                    }
                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.show()
                }

                user_delete_cancle.onClick {
                    adapter.notifyItemChanged(adapterPosition)
                    adapter.removedPosition = -1
                }

            } else {
                user_list_layout.visibility = View.VISIBLE
                user_list_delete_layout.visibility = View.GONE

                user_name.text = player.name
                user_email.text = player.email
                if (player.picture != null) {

                    user_picture.imageTintList = null

                    val bitmap = player.picture
                    val circleBitmap = Bitmap.createBitmap(bitmap!!.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888)

                    val shader = BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP)
                    val paint = Paint()
                    paint.setShader(shader)
                    paint.setAntiAlias(true)
                    val c = Canvas(circleBitmap)
                    val bitmapWidth: Float = (bitmap.width / 2).toFloat()
                    val bitmapHeigth: Float = (bitmap.height / 2).toFloat()
                    c.drawCircle(bitmapWidth, bitmapHeigth, bitmapWidth, paint)


                    user_picture.setImageBitmap(circleBitmap)

                    //user_picture.imageTintList = null
                    //user_picture.scaleType = ImageView.ScaleType.FIT_XY
                    //user_picture.imageBitmap = player.picture
                }

                layout_check.onClick {
                    player.checked = if (player.checked) false else true
                    user_check.visibility = if (player.checked) View.VISIBLE else View.INVISIBLE
                }

                layout_keep.onClick {
                    player.keep = !player.keep
                    user_keep.imageTintList = when(player.keep) {
                        true -> null
                        else -> ColorStateList.valueOf(resources.getColor(R.color.LightGrey))
                    }
                }

            }
        }
    }

}
