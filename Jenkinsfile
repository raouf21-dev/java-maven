pipeline{
    
    agent any
    environment {
        NEW_VERSION = "1.3.0"
    }
    stages{
        
        stage("build"){
            steps{
                echo "building the application..."
                echo "Buildin version ${NEW_VERSION}"
            }
        }

        stage("test"){
            steps{
                echo "testing the application..."
            }
        }
    
        stage("deploy"){
            steps{
                echo "deploying the application..."
                withCredentials([
                    usernamePassword(credentials: 'git-creds', usernameVariable: USER, passwordVariable: PWD)
                ]) {
                    sh "Some script ${USER} ${PWD}"
                }
            }
        }
    }
}
