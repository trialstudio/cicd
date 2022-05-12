pipelineJob('seedJob') {
    properties {
        pipelineTriggers {
            triggers {
                pollSCM {
                    scmpoll_spec('0 * * * *')
                }
                githubPush()
            }
        }
    }
    definition {
        cps {
            script('''
                @Library('job-dsl') _
                main.initialize()
                '''.stripIndent().trim())
        }
    }
}