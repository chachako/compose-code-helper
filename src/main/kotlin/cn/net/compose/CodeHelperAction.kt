package cn.net.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cn.net.compose.ui.components.SearchBar
import cn.net.compose.ui.theme.WidgetTheme
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.event.*
import javax.swing.border.Border
import javax.swing.border.EmptyBorder
import javax.swing.*
import androidx.compose.runtime.*
import cn.net.compose.utils.CodeHelper
import cn.net.compose.utils.Utils
import com.intellij.openapi.ui.DialogWrapper.OK_EXIT_CODE
import com.intellij.openapi.ui.DialogWrapperDialog
import com.intellij.openapi.ui.DialogWrapperPeer
import com.intellij.openapi.ui.impl.DialogWrapperPeerImpl
import com.intellij.openapi.wm.WindowManager
import com.jetbrains.rd.util.remove
import org.jetbrains.kotlin.idea.caches.project.NotUnderContentRootModuleInfo
import org.jetbrains.kotlin.preloading.ProfilingInstrumenterExample.e
import java.lang.ref.WeakReference

class CodeHelperAction : DumbAwareAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val dialogRef = CodeHelper.dialogs[e.project]?.get()
        if (dialogRef != null && dialogRef.isVisible) {
            dialogRef.close(DialogWrapper.CLOSE_EXIT_CODE)
            CodeHelper.dialogs[e.project!!] = null
        } else {
            val dialog = CodeHelperDialog(e.project)
            CodeHelper.dialogs[e.project!!] = WeakReference(dialog)
            dialog.show()
        }
    }
}
