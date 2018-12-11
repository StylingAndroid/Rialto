package androidx.appcompat.app

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.stylingandroid.rialto.CoroutineScopeConsumer
import com.stylingandroid.rialto.app.CoroutineLifecycleHandler
import kotlinx.coroutines.CoroutineScope

internal class ViewFactory private constructor(
    private val coroutineScope: CoroutineScope,
    private val viewInflater: RialtoViewInflater,
    private val contextProvider: (context: Context) -> Context
) : LayoutInflater.Factory2 {

    constructor(
        lifecycleOwner: LifecycleOwner,
        contextProvider: (context: Context) -> Context
    ) : this(CoroutineLifecycleHandler(lifecycleOwner), RialtoViewInflater(), contextProvider)

    override fun onCreateView(parent: View?, name: String?, context: Context, attrs: AttributeSet): View? =
        viewInflater.createView(parent, name, contextProvider(context), attrs, false, true, true, true).also {
            if(it is CoroutineScopeConsumer) it.setCoroutineScope(coroutineScope)
        }

    override fun onCreateView(name: String?, context: Context, attrs: AttributeSet): View? =
        onCreateView(null, name, context, attrs)

}
