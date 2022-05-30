import groovy.text.SimpleTemplateEngine

def initialize() {
    def templateEngine = new SimpleTemplateEngine()
    def renderer = { template, bindings ->
        templateEngine.createTemplate("${libraryResource template}").make(bindings).toString()
    }

    node('built-in') {
        stage('create or update jobs') {
            def teamApps = readYaml text: "${libraryResource 'team-apps.yaml'}"

            teamApps.each { team ->
                team.apps.each { app ->
                    def defaultBindings = ["team": team.name, "app": app.name]
                    switch(app.type) {
                        case "springboot": springboot.addSpringbootPipelines(app.name, renderer, defaultBindings)
                            break
                        default: echo 'type not found'
                    }
                }
                utility.addCategorizedViewJobDsl(team.name,
                        "^(${team.apps.collect { it.name }.join('|')})::.*",
                        "^(.*)::.*")
            }
        }
    }
}