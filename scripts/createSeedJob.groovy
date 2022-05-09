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
        cpsScm {
            scm {
                git {
                    remote {
                        credentials('github')
                        url('https://github.com/trialstudio/cicd.git')
                    }
                    branch('main')
                    extensions {
                        cleanBeforeCheckout()
                        cloneOption {
                            shallow(true)
                            depth(1)
                            noTags(true)
                            timeout(3)
                            reference('')
                        }
                        pathRestriction {
                            includedRegions('scripts')
                            excludedRegions('')
                        }
                    }
                }
            }
            scriptPath('scripts/createSeedJobPipeline.groovy')
        }
    }
}