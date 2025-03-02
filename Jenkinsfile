def gv

pipeline{
    
    agent any
    tools {
        maven "maven-3.9.9"
    }
    environment{
        BRANCH_NAME= sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
    }
    stages{
        stage("test"){
            steps {
                echo "testing the application..."
                echo "Executing pipeline for branch $BRANCH_NAME"
            }
        }
        stage("build"){
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps{
                script{
                    echo "Building the application..."
                }
            }
        }
        stage("deploy"){
            when {
                    expression {
                        BRANCH_NAME == "main"
                    }
                }
            steps{
                script{
                    echo "Deploying the application..."
                }
            }
        }
    }
}
