#!/usr/bin/env groovy
// @Library('jenkins-sharded-library')

library identifier: 'jenkins-sharded-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'https://github.com/raouf21-dev/jenkins-shared-library.git',
     credentialsId: 'git-creds'])

def gv

pipeline{
    
    agent any
    tools {
        maven "maven-3.9.9"
    }
    environment{
        WEBHOOK_TEST = "Testing web-hook."
        BRANCH_NAME= sh(script: "echo ${env.GIT_BRANCH} | sed 's|origin/||'", returnStdout: true).trim()
    }
    stages{
        stage("test"){
            steps {
                echo "testing the application..."
                echo "Executing pipeline for branch $BRANCH_NAME"
                echo "$WEBHOOK_TEST"
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
                    buildImage("santana20095/demo-app:jma-3.1")
                    dockerLogin()
                    dockerPush "santana20095/demo-app:jma-3.1"
                }
            }
        }
    }
}
