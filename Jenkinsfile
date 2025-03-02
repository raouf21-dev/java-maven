def gv

pipeline{
    
    agent any
    tools {
        maven "maven-3.9.9"
    }
    environment {
        MULTIBRNACH_WEBHOOK = "Testing MULTIBRNACH_WEBHOOK"
    } 
    stages{
        stage("test"){
            steps {
                echo "testing the application..."
                // echo "Executing pipeline for branch $BRANCH_NAME"
                echo "$MULTIBRNACH_WEBHOOK"
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