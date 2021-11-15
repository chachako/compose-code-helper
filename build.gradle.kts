@file:Suppress("SpellCheckingInspection")

plugins {
  kotlin
  id("org.jetbrains.intellij")
  id("org.jetbrains.compose")
  id("idea")
}

repositories {
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  mavenCentral()
  google()
}

intellij {
  pluginName.set(rootProject.name)
  updateSinceUntilBuild.set(false)
  plugins.add("Kotlin")
  // If the intellij path is defined in 'gradle.properties' or 'local.properties' or environment variables, use it.
  findPropertyOrEnv("intellij.path")?.toString()?.let(localPath::set) ?: version.set("2020.3")
}

dependencies {
  implementation(compose.desktop.currentOs)
}