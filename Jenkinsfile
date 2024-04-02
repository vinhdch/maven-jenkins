pipeline {
  agent any
  stages {
    stage('Build source') {
      steps {
        bat 'mvn clean'
      }
    }

    stage('Run chrome') {
      steps {
        bat 'mvn test'
      }
    }

  }
}