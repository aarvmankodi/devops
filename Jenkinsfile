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
                echo 'Running application...'
                // Run the built JAR file to display the message in console logs
                sh 'java -jar target/atm-banking-system-1.0-SNAPSHOT.jar'
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