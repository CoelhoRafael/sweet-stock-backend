pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Dmaven.test.failure.ignore=true'
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                    sh 'docker stop << 'docker ps -a | grep -i 8080 | awk {'print $1'})''
                }
            }
        }
        stage('Deliver') {
            steps {
                    sh '/var/lib/jenkins/workspace/spring-java/scripts/deliver.sh'
                
            }
        }
        
    }
}
