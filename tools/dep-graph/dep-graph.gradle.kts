tasks.register("generateDependencyGraph") {
    group = "tools"
    description = "Generate dependency graph"

    val excludedModules = setOf(":lint")

    // Get all the modules that have a build.gradle.kts or build.gradle file
    val moduleList = allprojects.filter {
        if (it == rootProject) return@filter false
        if (it.path in excludedModules) return@filter false
        it.file("build.gradle.kts").exists() || it.file("build.gradle").exists()
    }.map { project ->
        project.path to File(
            project.rootDir,
            "tools/dep-graph/graphs/dep_graph${
                project.path.replace(":", "_").replace("-", "_")
            }",
        ).absolutePath
    }

    doLast {
        println("Generating dependency graph...")
        moduleList.forEach { (modulePath, moduleFilePath) ->
            println("Generating dependency graph for $modulePath")
            // Generate the .gv file in a temporary directory
            exec {
                commandLine(
                    "./gradlew", "generateModulesGraphvizText",
                    "--no-configuration-cache",
                    "-Pmodules.graph.output.gv=${moduleFilePath}.gv",
                    "-Pmodules.graph.of.module=${modulePath}",
                )
            }
            // Convert the .gv file to .svg
            exec {
                commandLine(
                    "dot", "-Tsvg", "${moduleFilePath}.gv", "-o", "${moduleFilePath}.svg",
                )
            }
            // Delete the .gv file
            delete("$moduleFilePath.gv")
            println("Dependency graph for $modulePath generated at ${moduleFilePath}.svg")
        }
    }
}
