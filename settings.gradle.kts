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

buildscript {
  configurations.all {
    resolutionStrategy.force("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
  }
}

gradleToolkitWithMeowoolManualSpec({
  // Make the project spotless
  enableSpotless()
  spotless { project ->
    val license = resolveLicenseOfREADME()
    val ktlintData = mapOf(
      "indent_size" to "4",
      "chain-wrapping" to "true",
      "modifier-order" to "true",
      "string-template" to "true",
      "no-semi" to "true",
      "no-unit-return" to "true",
      "no-unused-imports" to "true",
      "no-trailing-spaces" to "true",
      "no-wildcard-imports" to "true",
      "no-line-break-before-assignment" to "true",
      "experimental:multiline-if-else" to "true",
      "experimental:double-colon-spacing" to "true",
      "experimental:spacing-between-declarations-with-comments" to "true",
    )
    kotlinGradle {
      ktlint().userData(ktlintData)
      endWithNewline()
      trimTrailingWhitespace()
      licenseHeader(license, "(import |plugins|buildscript|tasks|apply|rootProject|android|@)")
    }
    kotlin {
      ktlint().userData(ktlintData)
      target(project.sourceSets.flatMap { set ->
        set.allSource.sourceDirectories.asFileTree.filter { it.extension == "kt" }
      })
      endWithNewline()
      trimTrailingWhitespace()
      licenseHeader(license, "(package |import |class |fun |val |public |private |internal |@)")
    }
  }
})

fun resolveLicenseOfREADME(): String {
  var readStart = false
  val builder = StringBuilder().appendLine("/*")
  rootDir.resolve("README.md").useLines { lines ->
    lines.forEach {
      when {
        it == "<!--license start-->" -> readStart = true
        it == "<!--license end-->" -> return builder.append(" */").toString()
        readStart && it != "```" -> builder.append(" * ").appendLine(it)
      }
    }
  }
  error("Reading license failed")
}