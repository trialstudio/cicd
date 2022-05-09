pipelineJob('seedJob') {
    properties {
        pipelineTriggers {
            triggers {
                cron {
                    spec('H/10 * * * *')
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