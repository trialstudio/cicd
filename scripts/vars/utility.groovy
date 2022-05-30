def addMultiBranchPipelinePullRequestJobDsl(String jobName, String renderedPipeline) {
    def scriptTxt = """
        multibranchPipelineJob('${jobName}') {
          configure {
            it / factory(class: 'org.jenkinsci.plugins.inlinepipeline.InlineDefinitionBranchProjectFactory', plugin: "inline-pipeline@1.0.1") {
              script('''${renderedPipeline}''')
              sandbox(true)
              markerFile('README.md')
            }
          }
          
          branchSources {
            branchSource {
              source {
                github {
                  repoOwner('trialstudio')
                  repositoryUrl('https://github.com/trialstudio/zz.git')
                  configuredByUrl(true)
                  repository('zz')
                  credentialsId('github')
                  traits {
                    gitHubBranchDiscovery {
                      strategyId 2
                    }
                  }
                }
              }
            }
          }
        }
        """

    addJobDsl(scriptTxt)
}

def addPipelineJobDsl(String jobName, String renderedPipeline) {
    def scriptTxt = """
            pipelineJob('$jobName') {
                definition {
                    cps {
                        script('''${renderedPipeline}''')
                    }
                }
            }
            """

    addJobDsl(scriptTxt)
}

def addCategorizedViewJobDsl(String viewName, String jobRegex, String regexGroupingRule) {
    def categorizedViewTxt = """
                categorizedJobsView(/${viewName}/) {
                    jobs {
                        regex('${jobRegex}')
                        names('')
                    }
                    categorizationCriteria {
                        regexGroupingRule(/${regexGroupingRule}/)
                    }
                    columns {
                        status()
                        categorizedJob()
                        lastSuccess()
                        lastFailure()
                        lastDuration()
                        buildButton()
                    }
                }
                """

    addJobDsl(categorizedViewTxt)
}

def addJobDsl(String scriptTxt) {
    jobDsl failOnSeedCollision: true,
            ignoreMissingFiles: false,
            ignoreExisting: false,
            removedConfigFilesAction: 'DELETE',
            removedJobAction: 'DELETE',
            removedViewAction: 'DELETE',
            sandbox: false,
            unstableOnDeprecation: true,
            scriptText: scriptTxt
}