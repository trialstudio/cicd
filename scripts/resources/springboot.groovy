pipeline {
    agent {
        kubernetes {
            yaml """
                apiVersion: v1
                kind: Pod
                spec:
                  volumes:
                    - name: jenkins-maven-settings
                      configMap:
                        name: jenkins-maven-settings
                    - name: m2-repository
                      emptyDir: {}
                  containers:
                    - name: maven
                      image: maven:3.8.5-eclipse-temurin-17
                      command:
                        - sleep
                      args:
                        - infinity
                      volumeMounts:
                        - mountPath: /root/.m2
                          name: jenkins-maven-settings
                        - mountPath: /root/.m2/repository
                          name: m2-repository
                    - name: kaniko
                      image: gcr.io/kaniko-project/executor:debug
                      command:
                        - sleep
                      args:
                        - infinity
                """
        }
    }
    stages {
        stage('maven build') {
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/trialstudio/${app}.git'
                container('maven') {
                    sh 'mvn package'
                }
            }
        }
        stage('push to registry') {
            steps {
                container('kaniko') {
                    writeFile file: 'Dockerfile', text: """
                         FROM nexus-docker-registry:8000/eclipse-temurin:17-jre
                         COPY target/*.jar app.jar
                         ENTRYPOINT ["java", "-jar", "app.jar"]
                         """
                    sh '/kaniko/executor --no-push --context ./ --force --insecure-pull'
                }
            }
        }
    }
}
