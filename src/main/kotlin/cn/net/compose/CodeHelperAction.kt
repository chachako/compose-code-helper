package cn.net.compose

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.awt.ComposePanel
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent

/**
 * @author 凛 (https://github.com/RinOrz)
 */
class CodeHelperAction : DumbAwareAction() {
  override fun actionPerformed(e: AnActionEvent) {
    DemoDialog(e.project).show()
  }

  class DemoDialog(project: Project?) : DialogWrapper(project) {
    init {
      title = "Demo"
      init()
    }

    override fun createCenterPanel(): JComponent {
      return ComposePanel().apply {
        setBounds(0, 0, 800, 600)
        setContent {
          Button({}) { Text("Hello world!") }
        }
      }
    }
  }
}