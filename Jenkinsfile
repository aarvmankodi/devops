pipeline {
    agent any

    tools {
        maven 'Maven' // Must match name defined in Jenkins -> Manage Jenkins -> Tools
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/aarvmankodi/devops.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy (Run App)') {
    steps {
        echo 'Running ATM Banking System in non-interactive mode...'

        // Feed "4" to simulate user selecting "Exit"
        sh '''
            echo "4" | java -jar target/atm-banking-system-1.0-SNAPSHOT.jar
        '''
    }
}
    }

    post {
        always {
            echo 'Build pipeline completed.'
            junit '**/target/surefire-reports/*.xml'
        }
    }
}