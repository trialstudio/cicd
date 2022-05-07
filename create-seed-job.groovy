job ('seed-job') {
    description('Seed job that is to be referenced in Configuration as Code plugin')

    triggers {
        pollSCM {
            scmpoll_spec('H/10 * * * *')
        }
    }

    properties {
        authorizeProjectProperty {
            githubProjectUrl('https://github.com/trialstudio/cicd.git')
            strategy {
                triggeringUsersAuthorizationStrategy()
            }
        }
    }

    scm {
        git {
            remote {
                credentials('github')
                url('git@github.com:trialstudio/cicd.git')
            }
            branch('main')
            extensions {
                cleanBeforeCheckout()
                cloneOption {
                    shalllow(true)
                    depth(1)
                    noTags(true)
                }
                sparseCheckoutPaths {
                    sparseCheckoutPaths {
                        sparseCheckoutPath {
                            path('seed-job.groovy')
                        }
                    }
                }
            }
        }
    }

    job {
        steps {
            dsl {
                external('seed-job.groovy')
                removeAction('DELETE')
                removeViewAction('DELETE')
                ignoreExisting()
                additionalClasspath('lib')
            }
        }
    }
}