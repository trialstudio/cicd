job ('seedJob') {
    description('Seed job that is to be referenced in Configuration as Code plugin')

    triggers {
        pollSCM {
            scmpoll_spec('H/10 * * * *')
        }
    }

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
                sparseCheckoutPaths {
                    sparseCheckoutPaths {
                        sparseCheckoutPath {
                            path('seedJob.groovy')
                        }
                    }
                }
            }
        }
    }

    steps {
        dsl {
            external('seedJob.groovy')
            removeAction('DELETE')
            removeViewAction('DELETE')
            additionalClasspath('lib')
        }
    }
}