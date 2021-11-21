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
@file:Suppress("SpellCheckingInspection")

import org.jetbrains.changelog.markdownToHTML

plugins {
    kotlin
    id("com.meowool.sweekt")
    id("org.jetbrains.intellij")
    id("org.jetbrains.compose")
    id("org.jetbrains.changelog")
    id("idea")
}

repositories {
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenCentral()
    google()
}

dependencies {
    implementationOf(
        Libs.Kotlin.Stdlib.Jdk8,
        Libs.KotlinX.Coroutines.Core,
        compose.desktop.currentOs,
        compose.desktop.components.splitPane,
    )
}

kotlinJvmOptions {
    apiVersion = "1.5"
    languageVersion = apiVersion
    optIn("org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi")
}

compose.desktop.application.mainClass = "cn.net.compose.ComposeCodeHelper"

configurations.all {
    resolutionStrategy {
        eachDependency {
            if (requested.group == "org.jetbrains.kotlin") useVersion("1.5.31")
        }
        cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }
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
        pluginDescription.set(
            provider {
                val chinese = resolveDescriptionOfMarkdown("README.md")
                val english = resolveDescriptionOfMarkdown("README.en.md")
                markdownToHTML(
                    """
                        |$english
                        |***
                        |$chinese
                    """.trimIndent().trimMargin()
                )
            }
        )
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
