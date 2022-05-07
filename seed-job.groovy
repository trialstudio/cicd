def repos = new groovy.json.JsonSlurper().parse(streamFileFromWorkspace('test.json'))
def teamPermissions = [
    "hudson.model.Item.Build",
    "hudson.model.Item.Cancel",
    "hudson.model.Item.Read",
    "hudson.model.Item.Workspace",
    "com.cloudbees.plugins.credentials.CredentialsProvider.View"
]

for (team in repos.teams) {

    for (app in team.apps) {
        job(app.name) {
            description "Build and test the ${app.name}."
            steps {
                shell("echo ${team.name} ${app.type}")
            }
            properties {
                authorizationMatrix {
                    permissions(teamPermissions.collect { "GROUP:" + it + ":trialstudio*${team.name}" })
                }
                authorizeProjectProperty {
                    strategy {
                        triggeringUsersAuthorizationStrategy()
                    }
                }
            }
        }
    }

    listView("${team.name}") {
        jobs {
            team.apps.each {
                name(it.name)
            }
        }
        columns {
            status()
            name()
            descriptionColumn()
            buildButton()
        }
    }

}