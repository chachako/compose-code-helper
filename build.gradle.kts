@file:Suppress("SpellCheckingInspection")

plugins {
  kotlin
  id("org.jetbrains.intellij")
  id("org.jetbrains.compose")
  id("org.jetbrains.changelog")
  id("com.meowool.sweekt")
  id("idea")
}

repositories {
  maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  mavenCentral()
  google()
}

dependencies {
  implementation(compose.desktop.currentOs)
}

intellij {
  pluginName.set(rootProject.name)
  updateSinceUntilBuild.set(false)
  plugins.add("Kotlin")
  // If the intellij path is defined in 'gradle.properties' or 'local.properties' or environment variables, use it.
  // FIXME: findPropertyOrEnv("intellij.path")?.toString()?.let(localPath::set) ?:
  version.set("2020.3")
}

tasks {
  patchPluginXml {
    version.set("1.0.0")
    sinceBuild.set("203")
    // Don't restrict the new version of Intellij-IEDA
    untilBuild.set(provider { null })
    // Extract Chinese and English description from README.*md
    pluginDescription.set(provider {
      val chinese = resolveDescriptionOfMarkdown("README.md")
      val english = resolveDescriptionOfMarkdown("README.en.md")
      """
        |$english
        |***
        |$chinese
      """.trimIndent().trimMargin()
    })
  }
}

fun resolveDescriptionOfMarkdown(name: String): String {
  var readStart = false
  val builder = StringBuilder()
  rootDir.resolve(name).useLines { lines ->
    lines.forEach {
      when {
        it == "<!--description start-->" -> readStart = true
        it == "<!--description end-->" -> return builder.toString()
        readStart -> builder.appendLine(it)
      }
    }
  }
  error("Reading description failed")
}