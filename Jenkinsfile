pipeline{
    
    agent any
    environment {
        NEW_VERSION = "1.3.0"
    }
    parameters {
    //   string(name: 'VERSION', defaultValue: ' ', description: '')
      choice(name: 'VERSION', choices['1.1.0', '1.3.0', '1.2.1'], description: '')
      booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    stages{
        
        stage("build"){
            steps{
                echo "building the application..."
                echo "Buildin version ${NEW_VERSION}"
            }
        }

        stage("test"){
            when {
                expression {
                    params.executeTests
                }
            }
            steps{
                echo "testing the application..."
            }
        }
    
        stage("deploy"){
            steps{
                echo "deploying the application..."
                echo "deploying version ${params.VERSION}"
            }
        }
    }
}
