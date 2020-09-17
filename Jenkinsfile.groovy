pipeline {
  triggers {
    agent any
    triggers {
        cron('H */4 * * 1-5')
    }
    agent { any }

    stages {
      stage('Check web') {
        steps {
          script {
            content = sh(script: "curl -L localhost:8080", returnStdout: true).trim
            if ( contant.contains('server1') ) {
              currentBuild.result = 'FAILURE'
              error("Server returned 'server1'")
            }
          }
        }
      }
    }
  }
}
