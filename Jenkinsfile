#!/user/bin/env groovy
@Library(jenkins-library-library)

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
        stage("build image"){
            steps{
                script{
                    buildImage()
                }
            }
        }
    }
}