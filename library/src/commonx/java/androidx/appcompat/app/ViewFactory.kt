package androidx.appcompat.app

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.stylingandroid.rialto.androidx.widget.RialtoEditText
import com.stylingandroid.rialto.androidx.widget.RialtoTextView

internal class ViewFactory private constructor(
        private val viewInflater: RialtoViewInflater,
        private val contextProvider: (context: Context) -> Context
) : LayoutInflater.Factory2 {

    constructor(
            contextProvider: (context: Context) -> Context
    ) : this(RialtoViewInflater(), contextProvider)

    override fun onCreateView(parent: View?, name: String?, context: Context, attrs: AttributeSet): View? =
            viewInflater.createView(parent, name, contextProvider(context), attrs, false, true, true, true).also {
                (it as? RialtoTextView)?.initialiseDelegate()
                (it as? RialtoEditText)?.initialiseDelegate()
            }

    override fun onCreateView(name: String?, context: Context, attrs: AttributeSet): View? =
            onCreateView(null, name, context, attrs)

}
