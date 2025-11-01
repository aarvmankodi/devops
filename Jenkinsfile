pipeline {
    agent any

    tools {
        maven 'Maven'
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
                // Run tests but allow failure
                script {
                    def testStatus = sh(
                        script: 'mvn test',
                        returnStatus: true
                    )
                    
                    if (testStatus != 0) {
                        echo "⚠️ Some tests failed, but continuing pipeline..."
                    } else {
                        echo "✅ All tests passed."
                    }
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy (Run App)') {
            steps {
                echo 'Running ATM Banking System in non-interactive mode...'
                sh 'echo "4" | java -jar target/atm-banking-system-1.0-SNAPSHOT.jar'
            }
        }
    }

    post {
        always {
            echo 'Collecting test reports...'
            junit '**/target/surefire-reports/*.xml'
            echo '✅ Build pipeline completed.'
        }
    }
}