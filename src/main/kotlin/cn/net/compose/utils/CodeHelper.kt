package cn.net.compose.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.lang.ref.WeakReference

object CodeHelper {

    val dialogs = mutableMapOf<Project, WeakReference<DialogWrapper>?>()

}
