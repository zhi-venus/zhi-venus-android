apply(plugin = "com.jraska.module.graph.assertion")

tasks.register("generateAppModuleGraph") {
    group = "tools"
    description = "Generate dependency graph"
    doLast {
        val modulePath = project.path
        val outputDir = File(
            project.projectDir.parentFile,
            "docs/dep_graph/app${project.path.replace(":", "_").replace("-", "_")}",
        )
        outputDir.parentFile.mkdirs()
        println("Generating App dependency graph...")
        // Step1. Generate the .gv file
        exec {
            commandLine(
                "${project.rootDir}/gradlew",
                "generateModulesGraphvizText",
                "-Pmodules.graph.output.gv=${outputDir}.gv",
                "-Pmodules.graph.of.module=${modulePath}",
                "--no-configuration-cache"
            )
        }
        if (!File("${outputDir}.gv").exists()) {
            println("Generate graph vil failed, and retry with cache")
            exec {
                commandLine(
                    "${project.rootDir}/gradlew",
                    "generateModulesGraphvizText",
                    "-Pmodules.graph.output.gv=${outputDir}.gv",
                    "-Pmodules.graph.of.module=${modulePath}",
                )
            }
        }
        // Step2. Generate the .svg file
        exec {
            commandLine(
                "dot", "-Tsvg", "${outputDir}.gv", "-o", "${outputDir}.svg",
            )
        }
    }
}
