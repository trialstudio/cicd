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
                            includedRegions('seedJob.groovy\nteam-apps.json')
                            excludedRegions('')
                        }
                    }
                }
            }
            scriptPath('createSeedJob-pipeline.groovy')
        }
    }
}

jobDsl targets: 'seedJob.groovy',
        failOnSeedCollision: true,
        removedConfigFilesAction: 'DELETE',
        removedJobAction: 'DELETE',
        removedViewAction: 'DELETE',
        sandbox: true,
        unstableOnDeprecation: true

pipelineJob('example') {
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
                            includedRegions('seedJob.groovy\nteam-apps.json')
                            excludedRegions('')
                        }
                    }
                }
            }
            scriptPath(readFileFromWorkspace('seedJob.groovy'))
        }
    }
}