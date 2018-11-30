package android.support.v7.app

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.stylingandroid.rialto.support.widget.RialtoEditText
import com.stylingandroid.rialto.support.widget.RialtoTextView

internal class RialtoViewInflater : AppCompatViewInflater() {

    override fun createTextView(context: Context, attrs: AttributeSet?): AppCompatTextView {
        return RialtoTextView(context, attrs)
    }

    override fun createEditText(context: Context, attrs: AttributeSet?): AppCompatEditText {
        return RialtoEditText(context, attrs)
    }
}
