def gv

pipeline{
    
    agent any
    tools {
        maven "maven-3.9.9"
    }
    environment{
        WEBHOOK_TEST = "Testing web-hook..."
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
