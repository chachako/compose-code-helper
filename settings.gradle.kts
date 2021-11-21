/*
 * Copyright (c) 2021. The Compose Museum Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    https://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * In addition, if you modified the project, you must include the Compose Museum
 * organization URL in your code file: https://github.com/compose-museum/
 * 
 * 如果您修改了此项目，请确保源文件中包含 Compose 博物馆 URL: https://github.com/compose-museum/
 */
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
        resolutionStrategy {
            eachDependency {
                if (requested.group == "org.jetbrains.kotlin") useVersion("1.5.31")
            }
            cacheChangingModulesFor(0, TimeUnit.SECONDS)
        }
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
            licenseHeader(license, "(import |pluginManagement|plugins|buildscript|tasks|apply|rootProject|android|@)")
        }
        kotlin {
            ktlint().userData(ktlintData)
            target(
                project.sourceSets.flatMap { set ->
                    set.allSource.sourceDirectories.asFileTree.filter { it.extension == "kt" }
                }
            )
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
