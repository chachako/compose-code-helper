@file:Suppress("SpellCheckingInspection")

pluginManagement {
  repositories {
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

plugins {
  id("com.meowool.gradle.toolkit") version "0.1.0-SNAPSHOT"
}

gradleToolkit {
  publications {
    data {
      version = "1.0.0"
      displayName = "ComposeCodeHelper"
      groupId = "cn.net.compose"
      description = ""
      url = "https://github.com/compose-museum/${rootProject.name}"
      vcs = "$url.git"
      developer {
//        id = ""
//        name = ""
//        url = ""
      }
      developer {
        id = "rin"
        name = "Rin Orz"
        url = "https://github.com/RinOrz/"
      }
    }
  }
}