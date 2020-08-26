package weer.elytrondesign.core

import android.view.View

@FunctionalInterface
interface ViewTransformer {
    fun transform(view: View)
}