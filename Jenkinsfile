#!/user/bin/env groovy
// @Library('jenkins-sharded-library')

library identifier: "jenkins-sharded-library@main", retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'https://github.com/raouf21-dev/java-maven.git',
     credentialsId: 'git-creds'
    ]
)

def gv

pipeline{
    
    agent any
    tools {
        maven "maven-3.9.9"
    }
    stages{
        stage("test"){
            steps {
                echo "testing the application..."
                echo "Executing pipeline for branch $BRANCH_NAME"
            }
        }
        stage("build jar"){
            steps{
                script{
                    buildJar()
                }
            }
        }
        stage("build & push image"){
            steps{
                script{
                    buildImage("santana20095/demo-app:jma-3.0")
                    dockerLogin()
                    dockerPush "santana20095/demo-app:jma-3.0"
                }
            }
        }
    }
}