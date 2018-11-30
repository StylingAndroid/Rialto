package android.support.v7.app

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.stylingandroid.rialto.CoroutineScopeConsumer
import com.stylingandroid.rialto.app.CoroutineLifecycleHandler
import kotlinx.coroutines.CoroutineScope

internal class ViewFactory(
    private val lifecycleOwner: LifecycleOwner,
    private val coroutineScope: CoroutineScope = CoroutineLifecycleHandler(lifecycleOwner),
    private val viewInflater: RialtoViewInflater = RialtoViewInflater(),
    private val contextProvider: (context: Context) -> Context
) : LayoutInflater.Factory2 {
    override fun onCreateView(parent: View?, name: String?, context: Context, attrs: AttributeSet): View? =
        viewInflater.createView(parent, name, contextProvider(context), attrs, false, true, true, true).also {
            if (it is CoroutineScopeConsumer) it.setCoroutineScope(coroutineScope)
        }

    override fun onCreateView(name: String?, context: Context, attrs: AttributeSet): View? =
        onCreateView(null, name, context, attrs)

}
